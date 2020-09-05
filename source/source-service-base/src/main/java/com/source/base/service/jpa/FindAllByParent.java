package com.source.base.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface FindAllByParent<T, ID> extends FindAllService<T, ID> {

    /**
     * Tìm theo id lệ thuộc
     */

    <K> boolean deleteAllByParentID(ID idParent, Class<K> kClass);

    /**
     * Tìm theo id lệ thuộc
     */

    <K> T getByParentID(ID idParent, Class<K> kClass);

    <R, K> R getByParentID(ID idParent, Class<K> kClass, String column, Class<R> resultClass);

    <D, K> D getByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, String... columns);

    <K> Map<String, ?> getByParentID(ID idParent, Class<K> kClass, String... columns);

    /**
     * Tìm theo id lệ thuộc
     */

    <K> List<T> getAllByParentID(ID id, Class<K> kClass);

    <K> List<T> getAllByParentID(ID id, Class<K> kClass, Sort sort);

    <K> Page<T> getAllByParentID(ID id, Class<K> kClass, Pageable pageable, boolean count);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    <R, K> List<R> getAllByParentID(ID idParent, Class<K> kClass, String column, Class<R> resultClass);

    <R, K> List<R> getAllByParentID(ID idParent, Class<K> kClass, Sort sort, String column, Class<R> resultClass);

    <R, K> Page<R> getAllByParentID(ID idParent, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    <D, K> List<D> getAllByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, String... columns);

    <D, K> List<D> getAllByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns);

    <D, K> Page<D> getAllByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    <K> List<Map<String, Object>> getAllByParentID(ID id, Class<K> kClass, String... columns);

    <K> List<Map<String, Object>> getAllByParentID(ID id, Class<K> kClass, Sort sort, String... columns);

    <K> Page<Map<String, Object>> getAllByParentID(ID id, Class<K> kClass, Pageable pageable, boolean count, String... columns);

    /**
     *
     */

    <K> T getByParentName(String name, Object value, Class<K> kClass);

    <R, K> R getByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass);

    <D, K> D getByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns);

    <K> Map<String, ?> getByParentName(String name, Object value, Class<K> kClass, String... columns);

    /**
     *
     */

    <K> List<T> getAllByParentName(String name, Object value, Class<K> kClass);

    <K> List<T> getAllByParentName(String name, Object value, Class<K> kClass, Sort sort);

    <K> Page<T> getAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count);

    /**
     *
     */

    <R, K> List<R> getAllByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass);

    <R, K> List<R> getAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String column, Class<R> resultClass);

    <R, K> Page<R> getAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass);

    /**
     *
     */

    <D, K> List<D> getAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns);

    <D, K> List<D> getAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns);

    <D, K> Page<D> getAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns);

    /**
     *
     */

    <K> List<Map<String, Object>> getAllByParentName(String name, Object value, Class<K> kClass, String... columns);

    <K> List<Map<String, Object>> getAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String... columns);

    <K> Page<Map<String, Object>> getAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String... columns);
}