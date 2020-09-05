package com.source.base.utils;

import lombok.experimental.UtilityClass;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@UtilityClass
public class HttpUtils {

    // GET

    public <S> S getForEntity(RestTemplate restTemplate, String url, Class<S> typeRespone) {
        ResponseEntity<S> response = restTemplate.getForEntity(url, typeRespone);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return restTemplate.getForObject(url, typeRespone);
    }

    public <S> S getForEntity(RestTemplate restTemplate, String url, @Nullable Object request, Class<S> typeRespone, HttpHeaders httpHeaders) {
        ResponseEntity<S> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(request, httpHeaders), typeRespone);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return response.getBody();
    }

    public <S> S getForEntity(RestTemplate restTemplate, String url, HttpHeaders httpHeaders, Class<S> type) {
        ResponseEntity<S> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(null, httpHeaders), type);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return response.getBody();
    }

    public <S> S getForEntity(RestTemplate restTemplate, String url, String token, Class<S> type) {
        return getForEntity(restTemplate, url, HttpHeaderUtils.bearerAuth(token), type);
    }

    // POST

    public <S> S postForEntity(RestTemplate restTemplate, String url, @Nullable Object request, Class<S> type) {
        ResponseEntity<S> response = restTemplate.postForEntity(url, request, type);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return response.getBody();
    }

    public <S> S postForEntity(RestTemplate restTemplate, String url, @Nullable Object request, Class<S> typeRespone, String username, String password) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(username, password, StandardCharsets.UTF_8);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<S> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, httpHeaders), typeRespone);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return response.getBody();
    }

    public <S> S postForEntity(RestTemplate restTemplate, String url, @Nullable Object request, Class<S> typeRespone, HttpHeaders httpHeaders) {
        ResponseEntity<S> response = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(request, httpHeaders), typeRespone);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return response.getBody();
    }

    // DELETE

    public <S> S deleteForEntity(RestTemplate restTemplate, String url, @Nullable Object request, Class<S> typeRespone, HttpHeaders httpHeaders) {
        ResponseEntity<S> response = restTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(request, httpHeaders), typeRespone);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        return response.getBody();
    }
}
