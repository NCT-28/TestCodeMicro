package com.source.base.service.jpa;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface JpaService<T, ID> extends FindAllByParent<T, ID> {

    <S extends T> S add(S entity);

    <S extends T> S update(S entity);

    <S extends T> List<S> addAll(Iterable<S> entities);

    boolean existsById(ID id);

    // Cập nhật dữ liệu theo trường cố định
    boolean updateById(Map<String, Object> params, ID id);

    Set<T> saveAllMergeUnique(Iterable<T> entities, String name, String value);

    /**
     * Tìm kiếm
     */

    T getById(ID id);

    <R> R getById(ID id, String column, Class<R> resultClass);

    <D> D getById(ID id, Class<D> dtoClass, String... columns);

    Map<String, ?> getById(ID id, String... columns);

    /**
     * Tìm theo id lệ thuộc
     */

    <C> T getByChildID(ID idChild, Class<C> cClass);

    <R, C> R getByChildID(ID idChild, Class<C> cClass, String columns, Class<R> resultClass);

    <D, C> D getByChildID(ID idChild, Class<C> cClass, Class<D> dtoClass, String... columns);

    <C> Map<String, ?> getByChildID(ID idChild, Class<C> cClass, String... columns);

    /**
     * Tìm theo id lệ thuộc
     */

    <C> T getByChildName(String name, Object value, Class<C> cClass);

    <R, C> R getByChildName(String name, Object value, Class<C> cClass, String column, Class<R> resultClass);

    <D, C> D getByChildName(String name, Object value, Class<C> cClass, Class<D> dtoClass, String... columns);

    <C> Map<String, ?> getByChildName(String name, Object value, Class<C> cClass, String... columns);
}