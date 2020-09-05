package com.spring.jpa.base.service;

import com.source.base.service.jpa.ManyToManyService;
import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.repository.ManyToManyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Transactional(readOnly = true)
public abstract class ManyToManyImplService<T extends BaseEntity, TS extends BaseEntity, J extends BaseEntity> implements ManyToManyService<T, TS, J, UUID> {

    private final ManyToManyRepository<T, TS, J, UUID> repository;

    protected ManyToManyImplService(ManyToManyRepository<T, TS, J, UUID> repository) {
        this.repository = repository;
    }

    public ManyToManyRepository<T, TS, J, UUID> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public T saveEntityWithAllJoinTable(T entity, Iterable<J> entities) {
        return repository.saveEntityWithAllJoinTable(entity, entities);
    }

    @Override
    @Transactional
    public T saveEntityWithAllJoinTableSaved(T entity, Iterable<J> entities) {
        return repository.saveEntityWithAllJoinTableSaved(entity, entities);
    }

    @Override
    @Transactional
    public T saveEntityWithAllJoinTableUnique(T entity, Iterable<J> entities, String name, String value) {
        return repository.saveEntityWithAllJoinTableUnique(entity, entities, name, value);
    }

    @Override
    @Transactional
    public <S extends TS> S add(S entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public <S extends TS> List<S> addAll(Iterable<S> entities) {
        return repository.saveAll(entities);
    }

    @Override
    @Transactional
    public <S extends TS> S update(S entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public boolean delete(UUID uuid) {
        try {
            repository.deleteById(uuid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean existsById(UUID uuid) {
        return repository.existsById(uuid);
    }

    /**
     * With name column
     */

    @Override
    @Transactional
    public boolean updateById(Map<String, Object> params, UUID uuid) {
        return repository.updateById(params, uuid);
    }

    /**
     * With name column
     */

    @Override
    public List<J> getAllJoinTableByID(UUID uuid) {
        return repository.findAllJoinTableByID(uuid);
    }

    @Override
    public List<J> getAllJoinTableByID(UUID uuid, Sort sort) {
        return repository.findAllJoinTableByID(uuid, sort);
    }

    @Override
    public Page<J> getAllJoinTableByID(UUID uuid, Pageable pageable, boolean count) {
        return repository.findAllJoinTableByID(uuid, pageable, count);
    }


    /**
     * With name column
     */

    @Override
    public <R> List<R> getAllJoinTableByID(UUID uuid, String column, Class<R> rClass) {
        return repository.findAllJoinTableByID(uuid, column, rClass);
    }

    @Override
    public <R> List<R> getAllJoinTableByID(UUID uuid, Sort sort, String column, Class<R> rClass) {
        return repository.findAllJoinTableByID(uuid, sort, column, rClass);
    }

    @Override
    public <R> Page<R> getAllJoinTableByID(UUID uuid, Pageable pageable, boolean count, String column, Class<R> rClass) {
        return repository.findAllJoinTableByID(uuid, pageable, count, column, rClass);
    }

    /**
     * With name column
     */

    @Override
    public <D> List<D> getAllJoinTableByID(UUID uuid, Class<D> dClass, String... columns) {
        return repository.findAllJoinTableByID(uuid, dClass, columns);
    }

    @Override
    public <D> List<D> getAllJoinTableByID(UUID uuid, Class<D> dClass, Sort sort, String... columns) {
        return repository.findAllJoinTableByID(uuid, dClass, sort, columns);
    }

    @Override
    public <D> Page<D> getAllJoinTableByID(UUID uuid, Class<D> dClass, Pageable pageable, boolean count, String... columns) {
        return repository.findAllJoinTableByID(uuid, dClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<Map<String, Object>> getAllJoinTableByID(UUID uuid, String... columns) {
        return repository.findAllJoinTableByID(uuid, columns);
    }

    @Override
    public List<Map<String, Object>> getAllJoinTableByID(UUID uuid, Sort sort, String... columns) {
        return repository.findAllJoinTableByID(uuid, sort, columns);
    }

    @Override
    public Page<Map<String, Object>> getAllJoinTableByID(UUID uuid, Pageable pageable, boolean count, String... columns) {
        return repository.findAllJoinTableByID(uuid, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<J> getAllJoinTableByName(String name, Object value) {
        return repository.findAllJoinTableByName(name, value);
    }

    @Override
    public List<J> getAllJoinTableByName(String name, Object value, Sort sort) {
        return repository.findAllJoinTableByName(name, value, sort);
    }

    @Override
    public Page<J> getAllJoinTableByName(String name, Object value, Pageable pageable, boolean count) {
        return repository.findAllJoinTableByName(name, value, pageable, count);
    }

    /**
     * With name column
     */

    @Override
    public <R> List<R> getAllJoinTableByName(String name, Object value, String column, Class<R> rClass) {
        return repository.findAllJoinTableByName(name, value, column, rClass);
    }

    @Override
    public <R> List<R> getAllJoinTableByName(String name, Object value, Sort sort, String column, Class<R> rClass) {
        return repository.findAllJoinTableByName(name, value, sort, column, rClass);
    }

    @Override
    public <R> Page<R> getAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> rClass) {
        return repository.findAllJoinTableByName(name, value, pageable, count, column, rClass);
    }

    /**
     * With name column
     */

    @Override
    public <D> List<D> getAllJoinTableByName(String name, Object value, Class<D> dClass, String... columns) {
        return repository.findAllJoinTableByName(name, value, dClass, columns);
    }

    @Override
    public <D> List<D> getAllJoinTableByName(String name, Object value, Class<D> dClass, Sort sort, String... columns) {
        return repository.findAllJoinTableByName(name, value, dClass, sort, columns);
    }

    @Override
    public <D> Page<D> getAllJoinTableByName(String name, Object value, Class<D> dClass, Pageable pageable, boolean count, String... columns) {
        return repository.findAllJoinTableByName(name, value, dClass, pageable, count, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<Map<String, Object>> getAllJoinTableByName(String name, Object value, String... columns) {
        return repository.findAllJoinTableByName(name, value, columns);
    }

    @Override
    public List<Map<String, Object>> getAllJoinTableByName(String name, Object value, Sort sort, String... columns) {
        return repository.findAllJoinTableByName(name, value, sort, columns);
    }

    @Override
    public Page<Map<String, Object>> getAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String... columns) {
        return repository.findAllJoinTableByName(name, value, pageable, count, columns);
    }
}