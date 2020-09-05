package com.spring.jpa.base.repository;

import com.spring.jpa.base.sql.WhereSQL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BaseRepository<T, ID> extends IRepository<T, ID> {

    boolean updateById(Map<String, Object> params, ID id);

    Boolean checkExistByName(String name, String value);

    Set<T> saveAllMergeUnique(Iterable<T> entities, String name, String value);

    /**
     * Không dùng Search
     */

    <R> R findById(ID id, String column, Class<R> resultClass);

    <D> D findById(ID id, Class<D> dtoClass, String... columns);

    Map<String, ?> findById(ID id, String... columns);

    /**
     * With name column
     */

    T findByName(String name, Object value);

    <R> R findByName(String name, Object value, String column, Class<R> resultClass);

    <D> D findByName(String name, Object value, Class<D> dtoClass, String... columns);

    Map<String, ?> findByName(String name, Object value, String... columns);

    /**
     * With name column
     */

    List<T> findAllByName(String name, Object value);

    List<T> findAllByName(String name, Object value, Sort sort);

    Page<T> findAllByName(String name, Object value, Pageable pageable, boolean count);

    /**
     * With name column
     */

    <R> List<R> findAllByName(String name, Object value, String column, Class<R> resultClass);

    <R> List<R> findAllByName(String name, Object value, Sort sort, String column, Class<R> resultClass);

    <R> Page<R> findAllByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> resultClass);

    /**
     * With name column
     */

    <D> List<D> findAllByName(String name, Object value, Class<D> dtoClass, String... columns);

    <D> List<D> findAllByName(String name, Object value, Class<D> dtoClass, Sort sort, String... columns);

    <D> Page<D> findAllByName(String name, Object value, Class<D> dtoClass, Pageable pageable, boolean count, String... columns);

    /**
     * With name column
     */

    List<Map<String, Object>> findAllByName(String name, Object value, String... columns);

    List<Map<String, Object>> findAllByName(String name, Object value, Sort sort, String... columns);

    Page<Map<String, Object>> findAllByName(String name, Object value, Pageable pageable, boolean count, String... columns);

    /**
     *
     */

    <C> T findByChildID(ID idChild, Class<C> cClass);

    <R, C> R findByChildID(ID idChild, Class<C> cClass, String column, Class<R> resultClass);

    <D, C> D findByChildID(ID idChild, Class<C> cClass, Class<D> dtoClass, String... columns);

    <C> Map<String, ?> findByChildID(ID idChild, Class<C> cClass, String... columns);

    /**
     *
     */

    <C> T findByChildName(String name, Object value, Class<C> cClass);

    <R, C> R findByChildName(String name, Object value, Class<C> cClass, String column, Class<R> resultClass);

    <D, C> D findByChildName(String name, Object value, Class<C> cClass, Class<D> dtoClass, String... columns);

    <C> Map<String, ?> findByChildName(String name, Object value, Class<C> cClass, String... columns);

    /**
     *
     */

    <K> boolean deleteAllByParentID(ID idParent, Class<K> kClass);

    /**
     * @param idParent id cha cần lọc
     * @param kClass   entity map @{@link javax.persistence.ManyToOne}
     * @param <K>      Generic {@link Class<K>} entity map @{@link javax.persistence.ManyToOne}
     * @return danh sách cần tìm
     */

    <K> T findByParentID(ID idParent, Class<K> kClass);

    <R, K> R findByParentID(ID idParent, Class<K> kClass, String column, Class<R> resultClass);

    <D, K> D findByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, String... columns);

    <K> Map<String, ?> findByParentID(ID idParent, Class<K> kClass, String... columns);

    /**
     *
     */

    <K> List<T> findAllByParentID(ID idParent, Class<K> kClass);

    <K> List<T> findAllByParentID(ID idParent, Class<K> kClass, Sort sort);

    <K> Page<T> findAllByParentID(ID idParent, Class<K> kClass, Pageable pageable, boolean count);

    <R, K> List<R> findAllByParentID(ID idParent, Class<K> kClass, String column, Class<R> resultClass);

    <R, K> List<R> findAllByParentID(ID idParent, Class<K> kClass, Sort sort, String column, Class<R> resultClass);

    <R, K> Page<R> findAllByParentID(ID idParent, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass);

    <D, K> List<D> findAllByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, String... columns);

    <D, K> List<D> findAllByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns);

    <D, K> Page<D> findAllByParentID(ID idParent, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns);

    <K> List<Map<String, Object>> findAllByParentID(ID idParent, Class<K> kClass, String... columns);

    <K> List<Map<String, Object>> findAllByParentID(ID idParent, Class<K> kClass, Sort sort, String... columns);

    <K> Page<Map<String, Object>> findAllByParentID(ID idParent, Class<K> kClass, Pageable pageable, boolean count, String... columns);

    /**
     * @param kClass entity map @{@link javax.persistence.ManyToOne}
     * @param <K>    Generic {@link Class<K>} entity map @{@link javax.persistence.ManyToOne}
     * @return danh sách cần tìm
     */

    <K> T findByParentName(String name, Object value, Class<K> kClass);

    <R, K> R findByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass);

    <D, K> D findByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns);

    <K> Map<String, ?> findByParentName(String name, Object value, Class<K> kClass, String... columns);

    /**
     *
     */

    <K> List<T> findAllByParentName(String name, Object value, Class<K> kClass);

    <K> List<T> findAllByParentName(String name, Object value, Class<K> kClass, Sort sort);

    <K> Page<T> findAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count);

    <R, K> List<R> findAllByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass);

    <R, K> List<R> findAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String column, Class<R> resultClass);

    <R, K> Page<R> findAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass);

    <D, K> List<D> findAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns);

    <D, K> List<D> findAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns);

    <D, K> Page<D> findAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns);

    <K> List<Map<String, Object>> findAllByParentName(String name, Object value, Class<K> kClass, String... columns);

    <K> List<Map<String, Object>> findAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String... columns);

    <K> Page<Map<String, Object>> findAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String... columns);

    /**
     * Không dùng Search
     */

    Page<T> findAll(Pageable pageable, boolean count);

    List<Map<String, Object>> findAll(String... columns);

    List<Map<String, Object>> findAll(Sort sort, String... columns);

    Page<Map<String, Object>> findAll(Pageable pageable, boolean count, String... columns);

    /**
     * Không dùng Search
     */

    <R> List<R> findAll(String column, Class<R> resultClass);

    <R> List<R> findAll(Sort sort, String column, Class<R> resultClass);

    <R> Page<R> findAll(Pageable pageable, boolean count, String column, Class<R> resultClass);

    /**
     * @param dtoClass DTO map dữ liệu với các cột theo Constructor
     * @param columns  các cột cần lấy dữ liệu của Entity
     * @param <D>      Generic {@link Class<D>}
     * @return Danh sach cần tìm
     */
    <D> List<D> findAll(Class<D> dtoClass, String... columns);

    <D> List<D> findAll(Class<D> dtoClass, Sort sort, String... columns);

    <D> Page<D> findAll(Class<D> dtoClass, Pageable pageable, boolean count, String... columns);

    /*
    WhereSQL. Dùng để Search nhiều trường
    */
    List<T> findAll(WhereSQL whereSQL);

    List<T> findAll(WhereSQL whereSQL, Sort sort);

    Page<T> findAll(WhereSQL whereSQL, Pageable pageable, boolean count);

    <R> List<R> findAll(WhereSQL whereSQL, String column, Class<R> resultClass);

    <R> List<R> findAll(WhereSQL whereSQL, Sort sort, String column, Class<R> resultClass);

    <R> Page<R> findAll(WhereSQL whereSQL, Pageable pageable, boolean count, String column, Class<R> resultClass);

    <D> List<D> findAll(WhereSQL whereSQL, Class<D> dtoClass, String... columns);

    <D> List<D> findAll(WhereSQL whereSQL, Sort sort, Class<D> dtoClass, String... columns);

    <D> Page<D> findAll(WhereSQL whereSQL, Pageable pageable, boolean count, Class<D> dtoClass, String... columns);

    List<Map<String, Object>> findAll(WhereSQL whereSQL, String... columns);

    List<Map<String, Object>> findAll(WhereSQL whereSQL, Sort sort, String... columns);

    Page<Map<String, Object>> findAll(WhereSQL whereSQL, Pageable pageable, boolean count, String... columns);
}