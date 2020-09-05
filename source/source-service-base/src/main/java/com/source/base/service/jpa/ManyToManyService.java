package com.source.base.service.jpa;

import com.source.base.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

public interface ManyToManyService<T, TS, J, ID> extends IService<TS, ID> {

    <S extends TS> S add(S entity);

    <S extends TS> S update(S entity);

    <S extends TS> List<S> addAll(Iterable<S> entities);

    boolean existsById(ID id);

    T saveEntityWithAllJoinTable(T entity, Iterable<J> ts);

    T saveEntityWithAllJoinTableSaved(T entity, Iterable<J> entities);

    T saveEntityWithAllJoinTableUnique(T entity, Iterable<J> entities, String name, String value);

    /**
     * Cập nhật dữ liệu theo trường cố định
     *
     * @param params danh sách các cột cần cập nhật
     * @param id     id cần cập nhật
     * @return true: thành công  /  false: không thành công
     */
    boolean updateById(Map<String, Object> params, ID id);

    /**
     * Tìm danh sách bảng cần tìm có quan hệ với bảng nhiều nhiều
     * Ví dụ: bảng [Tài khoản] 1--n [Tài khoản - Vai trò] n--1 [Vai trò]
     * => sẽ tìm danh sách Vai trò theo ID của bảng Tài khoản
     *
     * @param id .
     * @return {@List null} danh sách cần tìm.
     */

    List<J> getAllJoinTableByID(ID id);

    List<J> getAllJoinTableByID(ID id, Sort sort);

    Page<J> getAllJoinTableByID(ID id, Pageable pageable, boolean count);

    <R> List<R> getAllJoinTableByID(ID id, String column, Class<R> rClass);

    <R> List<R> getAllJoinTableByID(ID id, Sort sort, String column, Class<R> rClass);

    <R> Page<R> getAllJoinTableByID(ID id, Pageable pageable, boolean count, String column, Class<R> rClass);

    <D> List<D> getAllJoinTableByID(ID id, Class<D> dClass, String... columns);

    <D> List<D> getAllJoinTableByID(ID id, Class<D> dClass, Sort sort, String... columns);

    <D> Page<D> getAllJoinTableByID(ID id, Class<D> dClass, Pageable pageable, boolean count, String... columns);

    List<Map<String, Object>> getAllJoinTableByID(ID id, String... columns);

    List<Map<String, Object>> getAllJoinTableByID(ID id, Sort sort, String... columns);

    Page<Map<String, Object>> getAllJoinTableByID(ID id, Pageable pageable, boolean count, String... columns);

    /**
     * Tìm danh sách bảng cần tìm có quan hệ với bảng nhiều nhiều
     * Ví dụ: bảng [Tài khoản] 1--n [Tài khoản - Vai trò] n--1 [Vai trò]
     * => sẽ tìm danh sách Vai trò theo ID của bảng Tài khoản
     *
     * @param name  tên cột
     * @param value giá trị cần tìm
     * @return {@List null} danh sách cần tìm.
     */
    List<J> getAllJoinTableByName(String name, Object value);

    List<J> getAllJoinTableByName(String name, Object value, Sort sort);

    Page<J> getAllJoinTableByName(String name, Object value, Pageable pageable, boolean count);

    <R> List<R> getAllJoinTableByName(String name, Object value, String column, Class<R> rClass);

    <R> List<R> getAllJoinTableByName(String name, Object value, Sort sort, String column, Class<R> rClass);

    <R> Page<R> getAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> rClass);

    <D> List<D> getAllJoinTableByName(String name, Object value, Class<D> dClass, String... columns);

    <D> List<D> getAllJoinTableByName(String name, Object value, Class<D> dClass, Sort sort, String... columns);

    <D> Page<D> getAllJoinTableByName(String name, Object value, Class<D> dClass, Pageable pageable, boolean count, String... columns);

    List<Map<String, Object>> getAllJoinTableByName(String name, Object value, String... columns);

    List<Map<String, Object>> getAllJoinTableByName(String name, Object value, Sort sort, String... columns);

    Page<Map<String, Object>> getAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String... columns);
}
