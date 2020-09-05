package com.base.source.models;

public interface INaturalIdEntity<T> extends IBaseEntity<T> {

    String getCode();

    void setCode(String code);
}
