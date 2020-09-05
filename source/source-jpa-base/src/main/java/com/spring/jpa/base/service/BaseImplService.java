package com.spring.jpa.base.service;

import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.repository.BaseRepository;
import com.spring.jpa.base.sql.WhereSQL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Transactional(readOnly = true)
public abstract class BaseImplService<T extends BaseEntity> implements WhereServiceSQL<T, UUID> {

    private final BaseRepository<T, UUID> repo;

    protected BaseImplService(BaseRepository<T, UUID> repo) {
        this.repo = repo;
    }

    public BaseRepository<T, UUID> getRepository() {
        return repo;
    }

    @Override
    @Transactional
    public boolean updateById(Map<String, Object> params, UUID uuid) {
        return repo.updateById(params, uuid);
    }

    @Override
    @Transactional
    public <S extends T> S add(S entity) {
        return repo.save(entity);
    }

    @Override
    @Transactional
    public <S extends T> List<S> addAll(Iterable<S> entities) {
        return repo.saveAll(entities);
    }

    @Override
    @Transactional
    public <S extends T> S update(S entity) {
        return repo.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public boolean delete(UUID uuid) {
        try {
            repo.deleteById(uuid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public Set<T> saveAllMergeUnique(Iterable<T> entities, String name, String value) {
        return repo.saveAllMergeUnique(entities, name, value);
    }

    @Override
    public boolean existsById(UUID uuid) {
        return repo.existsById(uuid);
    }

    /**
     * With name column
     */

    @Override
    public T getById(UUID id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public <R> R getById(UUID id, String column, Class<R> resultClass) {
        return repo.findById(id, column, resultClass);
    }

    @Override
    public <D> D getById(UUID id, Class<D> dtoClass, String... columns) {
        return repo.findById(id, dtoClass, columns);
    }

    @Override
    public Map<String, ?> getById(UUID id, String... columns) {
        return repo.findById(id, columns);
    }

    /**
     * With name column
     */

    @Override
    public Boolean checkExistByName(String name, String value) {
        return repo.checkExistByName(name, value);
    }

    /**
     * With name column
     */

    @Override
    public T getByName(String name, Object value) {
        return repo.findByName(name, value);
    }

    @Override
    public <R> R getByName(String name, Object value, String column, Class<R> resultClass) {
        return repo.findByName(name, value, column, resultClass);
    }

    @Override
    public <D> D getByName(String name, Object value, Class<D> dtoClass, String... columns) {
        return repo.findByName(name, value, dtoClass, columns);
    }

    @Override
    public Map<String, ?> getByName(String name, Object value, String... columns) {
        return repo.findByName(name, value, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<T> getAllByName(String name, Object value) {
        return repo.findAllByName(name, value);
    }

    @Override
    public List<T> getAllByName(String name, Object value, Sort sort) {
        return repo.findAllByName(name, value, sort);
    }

    @Override
    public Page<T> getAllByName(String name, Object value, Pageable pageable, boolean count) {
        return repo.findAllByName(name, value, pageable, count);
    }

    /**
     * With name column
     */

    @Override
    public <R> List<R> getAllByName(String name, Object value, String column, Class<R> resultClass) {
        return repo.findAllByName(name, value, null, column, resultClass);
    }

    @Override
    public <R> List<R> getAllByName(String name, Object value, Sort sort, String column, Class<R> resultClass) {
        return repo.findAllByName(name, value, sort, column, resultClass);
    }

    @Override
    public <R> Page<R> getAllByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return repo.findAllByName(name, value, pageable, count, column, resultClass);
    }

    /**
     * With name column
     */

    @Override
    public <D> List<D> getAllByName(String name, Object value, Class<D> dtoClass, String... columns) {
        return repo.findAllByName(name, value, dtoClass, columns);
    }

    @Override
    public <D> List<D> getAllByName(String name, Object value, Class<D> dtoClass, Sort sort, String... columns) {
        return repo.findAllByName(name, value, dtoClass, sort, columns);
    }

    @Override
    public <D> Page<D> getAllByName(String name, Object value, Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return repo.findAllByName(name, value, dtoClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<Map<String, Object>> getAllByName(String name, Object value, String... columns) {
        return repo.findAllByName(name, value, columns);
    }

    @Override
    public List<Map<String, Object>> getAllByName(String name, Object value, Sort sort, String... columns) {
        return repo.findAllByName(name, value, sort, columns);
    }

    @Override
    public Page<Map<String, Object>> getAllByName(String name, Object value, Pageable pageable, boolean count, String... columns) {
        return repo.findAllByName(name, value, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public <C> T getByChildID(UUID idChild, Class<C> cClass) {
        return repo.findByChildID(idChild, cClass);
    }

    @Override
    public <R, C> R getByChildID(UUID idChild, Class<C> cClass, String column, Class<R> resultClass) {
        return repo.findByChildID(idChild, cClass, column, resultClass);
    }

    @Override
    public <D, C> D getByChildID(UUID idChild, Class<C> cClass, Class<D> dtoClass, String... columns) {
        return repo.findByChildID(idChild, cClass, dtoClass, columns);
    }

    @Override
    public <C> Map<String, ?> getByChildID(UUID idChild, Class<C> cClass, String... columns) {
        return repo.findByChildID(idChild, cClass, columns);
    }

    /**
     * With name column
     */

    @Override
    public <C> T getByChildName(String name, Object value, Class<C> cClass) {
        return repo.findByChildName(name, value, cClass);
    }

    @Override
    public <R, C> R getByChildName(String name, Object value, Class<C> cClass, String column, Class<R> resultClass) {
        return repo.findByChildName(name, value, cClass, column, resultClass);
    }

    @Override
    public <D, C> D getByChildName(String name, Object value, Class<C> cClass, Class<D> dtoClass, String... columns) {
        return repo.findByChildName(name, value, cClass, dtoClass, columns);
    }

    @Override
    public <C> Map<String, ?> getByChildName(String name, Object value, Class<C> cClass, String... columns) {
        return repo.findByChildName(name, value, cClass, columns);
    }

    /**
     * With name column
     */

    @Override
    public <K> boolean deleteAllByParentID(UUID idParent, Class<K> kClass) {
        return repo.deleteAllByParentID(idParent, kClass);
    }

    /**
     * With name column
     */

    @Override
    public <K> T getByParentID(UUID idParent, Class<K> kClass) {
        return repo.findByParentID(idParent, kClass);
    }

    @Override
    public <R, K> R getByParentID(UUID idParent, Class<K> kClass, String column, Class<R> resultClass) {
        return repo.findByParentID(idParent, kClass, column, resultClass);
    }

    @Override
    public <D, K> D getByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return repo.findByParentID(idParent, kClass, dtoClass, columns);
    }

    @Override
    public <K> Map<String, ?> getByParentID(UUID idParent, Class<K> kClass, String... columns) {
        return repo.findByParentID(idParent, kClass, columns);
    }

    /**
     * With name column
     */

    @Override
    public <K> List<T> getAllByParentID(UUID idParent, Class<K> kClass) {
        return repo.findAllByParentID(idParent, kClass);
    }

    @Override
    public <K> List<T> getAllByParentID(UUID idParent, Class<K> kClass, Sort sort) {
        return repo.findAllByParentID(idParent, kClass, sort);
    }

    @Override
    public <K> Page<T> getAllByParentID(UUID idParent, Class<K> kClass, Pageable pageable, boolean count) {
        return repo.findAllByParentID(idParent, kClass, pageable, count);
    }

    /**
     * With name column
     */

    @Override
    public <R, K> List<R> getAllByParentID(UUID idParent, Class<K> kClass, String column, Class<R> resultClass) {
        return repo.findAllByParentID(idParent, kClass, column, resultClass);
    }

    @Override
    public <R, K> List<R> getAllByParentID(UUID idParent, Class<K> kClass, Sort sort, String column, Class<R> resultClass) {
        return repo.findAllByParentID(idParent, kClass, sort, column, resultClass);
    }

    @Override
    public <R, K> Page<R> getAllByParentID(UUID idParent, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return repo.findAllByParentID(idParent, kClass, pageable, count, column, resultClass);
    }

    /**
     * With name column
     */

    @Override
    public <D, K> List<D> getAllByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return repo.findAllByParentID(idParent, kClass, dtoClass, columns);
    }

    @Override
    public <D, K> List<D> getAllByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns) {
        return repo.findAllByParentID(idParent, kClass, dtoClass, sort, columns);
    }

    @Override
    public <D, K> Page<D> getAllByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return repo.findAllByParentID(idParent, kClass, dtoClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public <K> List<Map<String, Object>> getAllByParentID(UUID idParent, Class<K> kClass, String... columns) {
        return repo.findAllByParentID(idParent, kClass, columns);
    }

    @Override
    public <K> List<Map<String, Object>> getAllByParentID(UUID idParent, Class<K> kClass, Sort sort, String... columns) {
        return repo.findAllByParentID(idParent, kClass, sort, columns);
    }

    @Override
    public <K> Page<Map<String, Object>> getAllByParentID(UUID idParent, Class<K> kClass, Pageable pageable, boolean count, String... columns) {
        return repo.findAllByParentID(idParent, kClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public <K> T getByParentName(String name, Object value, Class<K> kClass) {
        return repo.findByParentName(name, value, kClass);
    }

    @Override
    public <R, K> R getByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass) {
        return repo.findByParentName(name, value, kClass, column, resultClass);
    }

    @Override
    public <D, K> D getByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return repo.findByParentName(name, value, kClass, dtoClass, columns);
    }

    @Override
    public <K> Map<String, ?> getByParentName(String name, Object value, Class<K> kClass, String... columns) {
        return repo.findByParentName(name, value, kClass, columns);
    }

    /**
     * With name column
     */

    @Override
    public <K> List<T> getAllByParentName(String name, Object value, Class<K> kClass) {
        return repo.findAllByParentName(name, value, kClass);
    }

    @Override
    public <K> List<T> getAllByParentName(String name, Object value, Class<K> kClass, Sort sort) {
        return repo.findAllByParentName(name, value, kClass, sort);
    }

    @Override
    public <K> Page<T> getAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count) {
        return repo.findAllByParentName(name, value, kClass, pageable, count);
    }

    /**
     * With name column
     */

    @Override
    public <R, K> List<R> getAllByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass) {
        return repo.findAllByParentName(name, value, kClass, column, resultClass);
    }

    @Override
    public <R, K> List<R> getAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String column, Class<R> resultClass) {
        return repo.findAllByParentName(name, value, kClass, sort, column, resultClass);
    }

    @Override
    public <R, K> Page<R> getAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return repo.findAllByParentName(name, value, kClass, pageable, count, column, resultClass);
    }

    /**
     * With name column
     */

    @Override
    public <D, K> List<D> getAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return repo.findAllByParentName(name, value, kClass, dtoClass, columns);
    }

    @Override
    public <D, K> List<D> getAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns) {
        return repo.findAllByParentName(name, value, kClass, dtoClass, sort, columns);
    }

    @Override
    public <D, K> Page<D> getAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return repo.findAllByParentName(name, value, kClass, dtoClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public <K> List<Map<String, Object>> getAllByParentName(String name, Object value, Class<K> kClass, String... columns) {
        return repo.findAllByParentName(name, value, kClass, columns);
    }

    @Override
    public <K> List<Map<String, Object>> getAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String... columns) {
        return repo.findAllByParentName(name, value, kClass, sort, columns);
    }

    @Override
    public <K> Page<Map<String, Object>> getAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String... columns) {
        return repo.findAllByParentName(name, value, kClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<T> getAll() {
        return repo.findAll();
    }

    @Override
    public List<T> getAll(Sort sort) {
        return repo.findAll(sort);
    }

    @Override
    public Page<T> getAll(Pageable pageable, boolean count) {
        return repo.findAll(pageable, count);
    }

    /**
     * With name column
     */

    @Override
    public List<Map<String, Object>> getAll(String... columns) {
        return repo.findAll(columns);
    }

    @Override
    public List<Map<String, Object>> getAll(Sort sort, String... columns) {
        return repo.findAll(sort, columns);
    }

    @Override
    public Page<Map<String, Object>> getAll(Pageable pageable, boolean count, String... columns) {
        return repo.findAll(pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public <R> List<R> getAll(String column, Class<R> resultClass) {
        return repo.findAll(column, resultClass);
    }

    @Override
    public <R> List<R> getAll(Sort sort, String column, Class<R> resultClass) {
        return repo.findAll(sort, column, resultClass);
    }

    @Override
    public <R> Page<R> getAll(Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return repo.findAll(pageable, count, column, resultClass);
    }

    /**
     * With name column
     */

    @Override
    public <D> List<D> getAll(Class<D> dtoClass, String... columns) {
        return repo.findAll(dtoClass, columns);
    }

    @Override
    public <D> List<D> getAll(Class<D> dtoClass, Sort sort, String... columns) {
        return repo.findAll(dtoClass, sort, columns);
    }

    @Override
    public <D> Page<D> getAll(Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return repo.findAll(dtoClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<T> getAll(WhereSQL whereSQL) {
        return repo.findAll(whereSQL);
    }

    @Override
    public List<T> getAll(WhereSQL whereSQL, Sort sort) {
        return repo.findAll(whereSQL, sort);
    }

    @Override
    public Page<T> getAll(WhereSQL whereSQL, Pageable pageable, boolean count) {
        return repo.findAll(whereSQL, pageable, count);
    }

    /**
     * With name column
     */

    @Override
    public <R> List<R> getAll(WhereSQL whereSQL, String column, Class<R> resultClass) {
        return repo.findAll(whereSQL, column, resultClass);
    }

    @Override
    public <R> List<R> getAll(WhereSQL whereSQL, Sort sort, String column, Class<R> resultClass) {
        return repo.findAll(whereSQL, sort, column, resultClass);
    }

    @Override
    public <R> Page<R> getAll(WhereSQL whereSQL, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return repo.findAll(whereSQL, pageable, count, column, resultClass);
    }

    /**
     * With name column
     */

    @Override
    public <D> List<D> getAll(WhereSQL whereSQL, Class<D> dtoClass, String... columns) {
        return repo.findAll(whereSQL, dtoClass, columns);
    }

    @Override
    public <D> List<D> getAll(WhereSQL whereSQL, Sort sort, Class<D> dtoClass, String... columns) {
        return repo.findAll(whereSQL, sort, dtoClass, columns);
    }

    @Override
    public <D> Page<D> getAll(WhereSQL whereSQL, Pageable pageable, boolean count, Class<D> dtoClass, String... columns) {
        return repo.findAll(whereSQL, pageable, count, dtoClass, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<Map<String, Object>> getAll(WhereSQL whereSQL, String... columns) {
        return repo.findAll(whereSQL, columns);
    }

    @Override
    public List<Map<String, Object>> getAll(WhereSQL whereSQL, Sort sort, String... columns) {
        return repo.findAll(whereSQL, sort, columns);
    }

    @Override
    public Page<Map<String, Object>> getAll(WhereSQL whereSQL, Pageable pageable, boolean count, String... columns) {
        return repo.findAll(whereSQL, pageable, count, columns);
    }
}