package com.source.base.service.rest;


import com.source.base.utils.HttpHeaderUtils;
import com.source.base.utils.HttpUtils;
import org.springframework.web.client.RestTemplate;

public abstract class BaseRestService<ID> {

    private final String host;
    private final String user;
    private final String pass;
    private final RestTemplate rest;

    public BaseRestService(RestTemplate rest, String host, String user, String pass) {
        this.host = host;
        this.user = user;
        this.pass = pass;
        this.rest = rest;
    }

    public RestTemplate getRestTemplate() {
        return rest;
    }

    public <K> K getForEntity(String path, Class<K> type) {
        return HttpUtils.getForEntity(rest, host + path, null, type, HttpHeaderUtils.basicAuth(user, pass));
    }

    public <K> K postForEntity(String path, Object obj, Class<K> type) {
        return HttpUtils.postForEntity(rest, host + path, obj, type, HttpHeaderUtils.basicAuth(user, pass));
    }

    public Boolean deleteForEntity(String path, ID id) {
        return HttpUtils.deleteForEntity(rest, host + path + "?id=" + id, null, Boolean.class, HttpHeaderUtils.basicAuth(user, pass));
    }
}