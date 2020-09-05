package com.source.base.service.rest;


import com.source.base.service.IService;

public interface RestService<T, ID> extends IService<T, ID> {

    T add(T entity);

    T update(T entity);

}
