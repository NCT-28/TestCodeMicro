package com.source.base.service.rest;


import com.source.base.controller.BaseCrudController;
import org.springframework.web.client.RestTemplate;

public abstract class BaseImplRestService<T, ID> extends BaseRestService<ID> implements RestService<T, ID> {

    private final Class<T> tClass;

    public BaseImplRestService(RestTemplate restTemplate, Class<T> tClass, String host, String username, String password) {
        super(restTemplate, host, username, password);
        this.tClass = tClass;
    }

    @Override
    public T add(T entity) {
        return this.postForEntity("/" + BaseCrudController._add, entity, tClass);
    }

    @Override
    public T update(T entity) {
        return this.postForEntity("/" + BaseCrudController._update, entity, tClass);
    }

    @Override
    public boolean delete(ID id) {
        return this.deleteForEntity("/" + BaseCrudController._delete, id);
    }
}