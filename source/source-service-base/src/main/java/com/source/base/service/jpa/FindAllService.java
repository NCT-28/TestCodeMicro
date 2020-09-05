package com.source.base.service.jpa;

import com.source.base.service.jpa.FindAllByNameService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface FindAllService<T, ID> extends FindAllByNameService<T, ID> {

    /**
     * Không dùng Search
     */

    List<T> getAll();

    List<T> getAll(Sort sort);

    Page<T> getAll(Pageable pageable, boolean count);

    /**
     * WhereSQL. Dùng để Search nhiều trường
     */


    List<Map<String, Object>> getAll(String... columns);

    List<Map<String, Object>> getAll(Sort sort, String... columns);

    Page<Map<String, Object>> getAll(Pageable pageable, boolean count, String... columns);

    /**
     * Không dùng Search
     */

    <R> List<R> getAll(String column, Class<R> resultClass);

    <R> List<R> getAll(Sort sort, String column, Class<R> resultClass);

    <R> Page<R> getAll(Pageable pageable, boolean count, String column, Class<R> resultClass);

    /**
     * @param dtoClass DTO map dữ liệu với các cột theo Constructor
     * @param columns  các cột cần lấy dữ liệu của Entity
     * @param <D>      Generic {@link Class<D>}
     * @return Danh sach cần tìm
     */

    <D> List<D> getAll(Class<D> dtoClass, String... columns);

    <D> List<D> getAll(Class<D> dtoClass, Sort sort, String... columns);

    <D> Page<D> getAll(Class<D> dtoClass, Pageable pageable, boolean count, String... columns);
}
