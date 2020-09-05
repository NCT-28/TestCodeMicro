package com.source.base.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class HttpHeaderUtils {
    public HttpHeaders bearerAuth(String token) {
        HttpHeaders h = new HttpHeaders();
        h.setBearerAuth(token);
        h.setContentType(MediaType.APPLICATION_JSON);
        return h;
    }

    public HttpHeaders basicAuth(String username, String password) {
        HttpHeaders h = new HttpHeaders();
        h.setBasicAuth(username, password, StandardCharsets.UTF_8);
        h.setContentType(MediaType.APPLICATION_JSON);
        return h;
    }
}