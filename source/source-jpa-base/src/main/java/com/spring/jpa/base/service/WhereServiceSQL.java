package com.spring.jpa.base.service;

import com.source.base.service.jpa.JpaService;
import com.spring.jpa.base.sql.WhereSQL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface WhereServiceSQL<T, ID> extends JpaService<T, ID> {

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    List<T> getAll(WhereSQL whereSQL);

    List<T> getAll(WhereSQL whereSQL, Sort sort);

    Page<T> getAll(WhereSQL whereSQL, Pageable pageable, boolean count);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    <R> List<R> getAll(WhereSQL whereSQL, String column, Class<R> resultClass);

    <R> List<R> getAll(WhereSQL whereSQL, Sort sort, String column, Class<R> resultClass);

    <R> Page<R> getAll(WhereSQL whereSQL, Pageable pageable, boolean count, String column, Class<R> resultClass);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */

    <D> List<D> getAll(WhereSQL whereSQL, Class<D> dtoClass, String... columns);

    <D> List<D> getAll(WhereSQL whereSQL, Sort sort, Class<D> dtoClass, String... columns);

    <D> Page<D> getAll(WhereSQL whereSQL, Pageable pageable, boolean count, Class<D> dtoClass, String... columns);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */


    List<Map<String, Object>> getAll(WhereSQL whereSQL, String... columns);

    List<Map<String, Object>> getAll(WhereSQL whereSQL, Sort sort, String... columns);

    Page<Map<String, Object>> getAll(WhereSQL whereSQL, Pageable pageable, boolean count, String... columns);
}