package com.base.dtos.jpa;

import java.util.Collection;

public interface ParentDTO<T> {
    T getIDParent();

    <K extends ParentDTO<T>> Collection<K> getChildren();

    <K extends ParentDTO<T>> void setChildren(Collection<K> collection);
}