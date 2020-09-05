package com.spring.base.utils;

import com.base.dtos.GrantedDTO;
import com.base.dtos.jwt.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class JwtUtils {

    private final SecretKey key = new SecretKeySpec("C&FJ@NcRfUjXn2r5u823df1232x/A%D*G-KaPd".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    /**
     * Generate Jwt from id and {@link org.springframework.security.core.GrantedAuthority}
     *
     * @param i id token
     * @param a user authority {@link GrantedDTO}
     * @return Jwt
     */
    public <T> String builder(T i, Collection<? extends GrantedDTO> a) {
        return builder(i, a.stream().map(GrantedDTO::getAuthority).toArray(String[]::new));
    }

    /**
     * Generate Jwt from id and {@link org.springframework.security.core.GrantedAuthority}
     *
     * @param i id token
     * @param a user authority {@link GrantedDTO}
     * @return Jwt
     */
    public <T> String builder(T i, String... a) {
        return Jwts.builder().signWith(key).setClaims(new HashMap<String, Object>() {{
            put(Claims.ID, i);
            put("roles", a);
            put(Claims.EXPIRATION, new Date(System.currentTimeMillis() + (3_600_000 * 20 * 10)));
        }}).compact();
    }

    /**
     * Validate Jwt Token
     *
     * @param t jwt
     * @return info {@link Claims}
     */
    public Claims validate(String t) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(t).getBody();
    }

    /**
     * Convert jwt info to {@link JwtToken}
     *
     * @param claims jwt {@link Claims}
     * @return {@link JwtToken}
     */
    public JwtToken parse(Claims claims) {
        return new JwtToken(claims.get(Claims.ID, String.class), claims.get("roles", ArrayList.class));
    }
}