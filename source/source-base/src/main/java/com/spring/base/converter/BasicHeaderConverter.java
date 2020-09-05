package com.spring.base.converter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class BasicHeaderConverter implements AuthenticationConverter {
    @Override
    public UsernamePasswordAuthenticationToken convert(HttpServletRequest r) {
        String h = r.getHeader(HttpHeaders.AUTHORIZATION);
        if (h == null) {
            return null;
        }
        h = h.trim();
        if (!StringUtils.startsWithIgnoreCase(h, "Basic")) {
            return null;
        }
        try {
            String t = new String(Base64.getDecoder().decode(h.substring(6).getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
            int l = t.indexOf(":");
            if (l == -1) {
                throw new BadCredentialsException("Invalid basic authentication token");
            }
            return new UsernamePasswordAuthenticationToken(t.substring(0, l), t.substring(l + 1));
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
    }
}