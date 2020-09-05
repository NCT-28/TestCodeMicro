package com.source.base.service.jpa;

import com.source.base.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface FindAllByNameService<T, ID> extends IService<T, ID> {

    /**
     * Tìm kiếm
     */

    Boolean checkExistByName(String name, String value);

    /**
     * Tìm kiếm
     */

    T getByName(String name, Object value);

    <R> R getByName(String name, Object value, String column, Class<R> resultClass);

    <D> D getByName(String name, Object value, Class<D> dtoClass, String... columns);

    Map<String, ?> getByName(String name, Object value, String... columns);

    /**
     * Tìm kiếm
     */

    List<T> getAllByName(String name, Object value);

    List<T> getAllByName(String name, Object value, Sort sort);

    Page<T> getAllByName(String name, Object value, Pageable pageable, boolean count);

    /**
     * With name column
     */

    <R> List<R> getAllByName(String name, Object value, String column, Class<R> resultClass);

    <R> List<R> getAllByName(String name, Object value, Sort sort, String column, Class<R> resultClass);

    <R> Page<R> getAllByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> resultClass);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    <D> List<D> getAllByName(String name, Object value, Class<D> dtoClass, String... columns);

    <D> List<D> getAllByName(String name, Object value, Class<D> dtoClass, Sort sort, String... columns);

    <D> Page<D> getAllByName(String name, Object value, Class<D> dtoClass, Pageable pageable, boolean count, String... columns);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    List<Map<String, Object>> getAllByName(String name, Object value, String... columns);

    List<Map<String, Object>> getAllByName(String name, Object value, Sort sort, String... columns);

    Page<Map<String, Object>> getAllByName(String name, Object value, Pageable pageable, boolean count, String... columns);
}