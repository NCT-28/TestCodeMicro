package com.spring.base.config;

import com.base.dtos.http.AuthorityHTTPEndpointsService;
import com.source.base.controller.constants.Path;
import com.spring.base.converter.BasicHeaderConverter;
import com.spring.base.utils.ErrorUtils;
import com.spring.base.utils.JwtUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cấu hình mặc định cho Server
 *
 * @author Nguyễn Đình Tạo
 * @see EnableWebSecurity
 */
public abstract class AbstractServerConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationConverter converter = new BasicHeaderConverter();

    public abstract PasswordEncoder encoder();

    protected abstract JwtUtils getJwtUtils();

    protected abstract AuthorityHTTPEndpointsService getService();

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses should not invoke this method by
     * calling super as it may override their configuration.
     *
     * @param h the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     */
    @Override
    public void configure(HttpSecurity h) throws Exception {
        // Init Paths
        var allows = new AntPathRequestMatcher(Path.ALLOWS_API_URL + "/**");
        var auth = new AntPathRequestMatcher(Path.AUTH_API_URL + "/**");
        var exchange = new AntPathRequestMatcher(Path.EXCHANGE_URL + "/**");
        // Conf
        h.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests(a -> a.requestMatchers(allows, auth, exchange).authenticated().anyRequest().permitAll())
                .addFilterBefore((q, s, c) -> {
                    HttpServletRequest req = (HttpServletRequest) q;
                    HttpServletResponse res = (HttpServletResponse) s;
                    res.setHeader("Access-Control-Max-Age", "3600");
                    res.setHeader("Access-Control-Allow-Origin", "*");
                    res.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
                    res.setHeader("Access-Control-Allow-Headers", HttpHeaders.AUTHORIZATION + "," + HttpHeaders.CONTENT_TYPE + "," + HttpHeaders.ACCEPT);
                    if ("OPTIONS".equals(req.getMethod())) {
                        res.setStatus(HttpServletResponse.SC_OK);
                    } else {
                        c.doFilter(req, s);
                    }
                }, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore((q, s, c) -> {
                    HttpServletRequest req = (HttpServletRequest) q;
                    if (allows.matches(req)) {
                        try {
                            SecurityContextHolder.getContext()
                                    .setAuthentication(getJwtUtils().parse(getJwtUtils().validate(req.getHeader(HttpHeaders.AUTHORIZATION).substring(7))));
                        } catch (Exception ex) {
                            ErrorUtils.send(s, "Không tìm thấy Token !");
                            throw new ServletException("Không tìm thấy Token !");
                        }
                    } else if (auth.matches(req)) {
                        try {
                            var jwt = getJwtUtils().parse(getJwtUtils().validate(req.getHeader(HttpHeaders.AUTHORIZATION).substring(7)));
                            if (getService().check(req.getServletPath(), jwt.getRoles())) {
                                ErrorUtils.send(s, "Bạn không có quyền truy cập !");
                                throw new ServletException("Bạn không có quyền truy cập !");
                            }
                            SecurityContextHolder.getContext().setAuthentication(jwt);
                        } catch (Exception e) {
                            ErrorUtils.send(s, "Không tìm thấy Token !");
                            throw new ServletException("Không tìm thấy Token !");
                        }
                    } else if (exchange.matches(req)) {
                        try {
                            var credentials = converter.convert(req);
                            if (credentials == null) {
                                return;
                            }
                            var authentication = authenticationManager().authenticate(credentials);
                            if (authentication == null) {
                                return;
                            }
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } catch (Exception e) {
                            SecurityContextHolder.clearContext();
                        }
                    }
                    c.doFilter(req, s);
                }, UsernamePasswordAuthenticationFilter.class)
        ;
    }
}