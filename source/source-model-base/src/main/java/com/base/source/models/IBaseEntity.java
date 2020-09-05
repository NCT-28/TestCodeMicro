package com.base.source.models;

import java.io.Serializable;

public interface IBaseEntity<T> extends IAuditEntity<String>, Serializable {

    T getUuid();
}