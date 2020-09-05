package com.spring.jpa.base.repository;

import com.spring.entity.base.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;

/**
 * @author Nguyễn Đình Tạo
 */
public interface ManyToManyRepository<T extends BaseEntity, TS extends BaseEntity, J extends BaseEntity, ID> extends IRepository<TS, ID> {

    T saveEntityWithAllJoinTable(T entity, Iterable<J> entities);

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
    List<J> findAllJoinTableByID(ID id);

    List<J> findAllJoinTableByID(ID id, Sort sort);

    Page<J> findAllJoinTableByID(ID id, Pageable pageable, boolean count);

    <R> List<R> findAllJoinTableByID(ID id, String column, Class<R> rClass);

    <R> List<R> findAllJoinTableByID(ID id, Sort sort, String column, Class<R> rClass);

    <R> Page<R> findAllJoinTableByID(ID id, Pageable pageable, boolean count, String column, Class<R> rClass);

    <D> List<D> findAllJoinTableByID(ID id, Class<D> dClass, String... columns);

    <D> List<D> findAllJoinTableByID(ID id, Class<D> dClass, Sort sort, String... columns);

    <D> Page<D> findAllJoinTableByID(ID id, Class<D> dClass, Pageable pageable, boolean count, String... columns);

    List<Map<String, Object>> findAllJoinTableByID(ID id, String... columns);

    List<Map<String, Object>> findAllJoinTableByID(ID id, Sort sort, String... columns);

    Page<Map<String, Object>> findAllJoinTableByID(ID id, Pageable pageable, boolean count, String... columns);

    /**
     * Tìm danh sách bảng cần tìm có quan hệ với bảng nhiều nhiều
     * Ví dụ: bảng [Tài khoản] 1--n [Tài khoản - Vai trò] n--1 [Vai trò]
     * => sẽ tìm danh sách Vai trò theo ID của bảng Tài khoản
     *
     * @param name  tên cột
     * @param value giá trị cần tìm
     * @return {@List null} danh sách cần tìm.
     */
    List<J> findAllJoinTableByName(String name, Object value);

    List<J> findAllJoinTableByName(String name, Object value, Sort sort);

    Page<J> findAllJoinTableByName(String name, Object value, Pageable pageable, boolean count);

    <R> List<R> findAllJoinTableByName(String name, Object value, String column, Class<R> rClass);

    <R> List<R> findAllJoinTableByName(String name, Object value, Sort sort, String column, Class<R> rClass);

    <R> Page<R> findAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> rClass);

    <D> List<D> findAllJoinTableByName(String name, Object value, Class<D> dClass, String... columns);

    <D> List<D> findAllJoinTableByName(String name, Object value, Class<D> dClass, Sort sort, String... columns);

    <D> Page<D> findAllJoinTableByName(String name, Object value, Class<D> dClass, Pageable pageable, boolean count, String... columns);

    List<Map<String, Object>> findAllJoinTableByName(String name, Object value, String... columns);

    List<Map<String, Object>> findAllJoinTableByName(String name, Object value, Sort sort, String... columns);

    Page<Map<String, Object>> findAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String... columns);
}