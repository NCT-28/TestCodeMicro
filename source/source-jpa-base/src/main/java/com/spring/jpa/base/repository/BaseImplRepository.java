package com.spring.jpa.base.repository;

import com.base.dtos.jpa.BaseEntityDTO;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.repository.query.FindAll;
import com.spring.jpa.base.repository.query.FindByChildID;
import com.spring.jpa.base.repository.query.FindByChildName;
import com.spring.jpa.base.repository.query.FindById;
import com.spring.jpa.base.repository.query.FindByName;
import com.spring.jpa.base.repository.query.FindByParentID;
import com.spring.jpa.base.repository.query.FindByParentName;
import com.spring.jpa.base.repository.query.WhereSQLFindAll;
import com.spring.jpa.base.repository.update.UpdateById;
import com.spring.jpa.base.sql.WhereSQL;
import com.spring.jpa.base.utils.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public abstract class BaseImplRepository<T> extends SimpleJpaRepository<T, UUID> implements BaseRepository<T, UUID> {

    private final EntityManager em;

    protected BaseImplRepository(Class<T> t, EntityManager e) {
        super(t, e);
        this.em = e;
    }

    public EntityManager getEM() {
        return this.em;
    }

    @Override
    public boolean updateById(Map<String, Object> params, UUID id) {
        return EMUtils.execute(em, UpdateById.generateQuery(em, getDomainClass(), params, id));
    }

    @Override
    public Set<T> saveAllMergeUnique(Iterable<T> entities, String name, String value) {
        Set<T> entitiesSave = new HashSet<>();
        for (T entity : entities) {
            entitiesSave.add(Objects.requireNonNullElseGet(this.findByName(name, value), () -> this.save(entity)));
        }
        return entitiesSave;
    }

    /**
     * Không dùng Search
     */

    @Override
    public boolean existsById(UUID id) {
        return findById(id).isPresent();
    }

    /**
     * Không dùng Search
     */

    @Override
    public Optional<T> findById(UUID id) {
        return Optional.ofNullable(FindById.get(em, getDomainClass(), id));
    }

    @Override
    public <R> R findById(UUID id, String column, Class<R> resultClass) {
        return FindById.get(em, getDomainClass(), id, column, resultClass);
    }

    @Override
    public <D> D findById(UUID id, Class<D> dtoClass, String... columns) {
        return FindById.get(em, getDomainClass(), dtoClass, id, columns);
    }

    @Override
    public Map<String, ?> findById(UUID id, String... columns) {
        return FindById.get(em, getDomainClass(), id, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public Boolean checkExistByName(String name, String value) {
        return findByName(name, value, BaseEntityDTO.class, baseIDName) != null;
    }

    /**
     * Không dùng Search
     */

    @Override
    public T findByName(String name, Object value) {
        return FindByName.get(em, name, value, getDomainClass());
    }

    @Override
    public <R> R findByName(String name, Object value, String column, Class<R> resultClass) {
        return FindByName.get(em, name, value, getDomainClass(), column, resultClass);
    }

    @Override
    public <D> D findByName(String name, Object value, Class<D> dtoClass, String... columns) {
        return FindByName.get(em, name, value, getDomainClass(), dtoClass, columns);
    }

    @Override
    public Map<String, ?> findByName(String name, Object value, String... columns) {
        return FindByName.get(em, name, value, getDomainClass(), columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public List<T> findAllByName(String name, Object value) {
        return FindByName.getList(em, null, name, value, getDomainClass());
    }

    @Override
    public List<T> findAllByName(String name, Object value, Sort sort) {
        return FindByName.getList(em, sort, name, value, getDomainClass());
    }

    @Override
    public Page<T> findAllByName(String name, Object value, Pageable pageable, boolean count) {
        return FindByName.getPage(em, pageable, count, name, value, getDomainClass());
    }

    /**
     * Không dùng Search
     */

    @Override
    public <R> List<R> findAllByName(String name, Object value, String column, Class<R> resultClass) {
        return FindByName.getList(em, null, name, value, getDomainClass(), column, resultClass);
    }

    @Override
    public <R> List<R> findAllByName(String name, Object value, Sort sort, String column, Class<R> resultClass) {
        return FindByName.getList(em, sort, name, value, getDomainClass(), column, resultClass);
    }

    @Override
    public <R> Page<R> findAllByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return FindByName.getPage(em, pageable, count, name, value, getDomainClass(), column, resultClass);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <D> List<D> findAllByName(String name, Object value, Class<D> dtoClass, String... columns) {
        return FindByName.getList(em, null, name, value, getDomainClass(), dtoClass, columns);
    }

    @Override
    public <D> List<D> findAllByName(String name, Object value, Class<D> dtoClass, Sort sort, String... columns) {
        return FindByName.getList(em, sort, name, value, getDomainClass(), dtoClass, columns);
    }

    @Override
    public <D> Page<D> findAllByName(String name, Object value, Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return FindByName.getPage(em, pageable, count, name, value, getDomainClass(), dtoClass, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public List<Map<String, Object>> findAllByName(String name, Object value, String... columns) {
        return FindByName.getList(em, null, name, value, getDomainClass(), columns);
    }

    @Override
    public List<Map<String, Object>> findAllByName(String name, Object value, Sort sort, String... columns) {
        return FindByName.getList(em, sort, name, value, getDomainClass(), columns);
    }

    @Override
    public Page<Map<String, Object>> findAllByName(String name, Object value, Pageable pageable, boolean count, String... columns) {
        return FindByName.getPage(em, pageable, count, name, value, getDomainClass(), columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <C> T findByChildID(UUID idChild, Class<C> childClass) {
        return FindByChildID.get(em, idChild, getDomainClass(), childClass);
    }

    @Override
    public <R, C> R findByChildID(UUID idChild, Class<C> childClass, String column, Class<R> resultClass) {
        return FindByChildID.get(em, idChild, getDomainClass(), childClass, column, resultClass);
    }

    @Override
    public <D, C> D findByChildID(UUID idChild, Class<C> childClass, Class<D> dtoClass, String... columns) {
        return FindByChildID.get(em, idChild, getDomainClass(), childClass, dtoClass, columns);
    }

    @Override
    public <C> Map<String, ?> findByChildID(UUID idChild, Class<C> childClass, String... columns) {
        return FindByChildID.get(em, idChild, getDomainClass(), childClass, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <C> T findByChildName(String name, Object value, Class<C> cClass) {
        return FindByChildName.get(em, name, value, getDomainClass(), cClass);
    }

    @Override
    public <R, C> R findByChildName(String name, Object value, Class<C> cClass, String column, Class<R> resultClass) {
        return FindByChildName.get(em, name, value, getDomainClass(), cClass, column, resultClass);
    }

    @Override
    public <D, C> D findByChildName(String name, Object value, Class<C> cClass, Class<D> dtoClass, String... columns) {
        return FindByChildName.get(em, name, value, getDomainClass(), cClass, dtoClass, columns);
    }

    @Override
    public <C> Map<String, ?> findByChildName(String name, Object value, Class<C> cClass, String... columns) {
        return FindByChildName.get(em, name, value, getDomainClass(), cClass, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <K> boolean deleteAllByParentID(UUID uuid, Class<K> kClass) {
        return EMUtils.execute(em, em.createQuery(
                "delete " + QueryUtils.GetNameEntity(getDomainClass()) +
                        " where " + QueryUtils.GetFieldNameByTypeClass(getDomainClass(), kClass) + "." + baseIDName + "=:p")
                .setParameter("p", uuid));
    }

    /**
     * Không dùng Search
     */

    @Override
    public <K> T findByParentID(UUID idParent, Class<K> kClass) {
        return FindByParentID.get(em, idParent, getDomainClass(), kClass);
    }

    @Override
    public <R, K> R findByParentID(UUID idParent, Class<K> kClass, String column, Class<R> resultClass) {
        return FindByParentID.get(em, idParent, getDomainClass(), kClass, column, resultClass);
    }

    @Override
    public <D, K> D findByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return FindByParentID.get(em, idParent, getDomainClass(), kClass, dtoClass, columns);
    }

    @Override
    public <K> Map<String, ?> findByParentID(UUID idParent, Class<K> kClass, String... columns) {
        return FindByParentID.get(em, idParent, getDomainClass(), kClass, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <K> List<T> findAllByParentID(UUID idParent, Class<K> kClass) {
        return FindByParentID.getList(em, idParent, null, getDomainClass(), kClass);
    }

    @Override
    public <K> List<T> findAllByParentID(UUID idParent, Class<K> kClass, Sort sort) {
        return FindByParentID.getList(em, idParent, sort, getDomainClass(), kClass);
    }

    @Override
    public <K> Page<T> findAllByParentID(UUID idParent, Class<K> kClass, Pageable pageable, boolean count) {
        return FindByParentID.getPage(em, idParent, pageable, getDomainClass(), kClass, count);
    }


    /**
     * Không dùng Search
     */

    @Override
    public <R, K> List<R> findAllByParentID(UUID idParent, Class<K> kClass, String column, Class<R> resultClass) {
        return FindByParentID.getList(em, idParent, null, getDomainClass(), kClass, column, resultClass);
    }

    @Override
    public <R, K> List<R> findAllByParentID(UUID idParent, Class<K> kClass, Sort sort, String column, Class<R> resultClass) {
        return FindByParentID.getList(em, idParent, sort, getDomainClass(), kClass, column, resultClass);
    }

    @Override
    public <R, K> Page<R> findAllByParentID(UUID idParent, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return FindByParentID.getPage(em, idParent, pageable, getDomainClass(), kClass, count, column, resultClass);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <D, K> List<D> findAllByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return FindByParentID.getList(em, idParent, null, getDomainClass(), kClass, dtoClass, columns);
    }

    @Override
    public <D, K> List<D> findAllByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns) {
        return FindByParentID.getList(em, idParent, sort, getDomainClass(), kClass, dtoClass, columns);
    }

    @Override
    public <D, K> Page<D> findAllByParentID(UUID idParent, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return FindByParentID.getPage(em, idParent, pageable, getDomainClass(), kClass, dtoClass, count, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <K> List<Map<String, Object>> findAllByParentID(UUID idParent, Class<K> kClass, String... columns) {
        return FindByParentID.getList(em, idParent, null, getDomainClass(), kClass, columns);
    }

    @Override
    public <K> List<Map<String, Object>> findAllByParentID(UUID idParent, Class<K> kClass, Sort sort, String... columns) {
        return FindByParentID.getList(em, idParent, sort, getDomainClass(), kClass, columns);
    }

    @Override
    public <K> Page<Map<String, Object>> findAllByParentID(UUID idParent, Class<K> kClass, Pageable pageable, boolean count, String... columns) {
        return FindByParentID.getPage(em, idParent, pageable, getDomainClass(), kClass, count, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <K> T findByParentName(String name, Object value, Class<K> kClass) {
        return FindByParentName.get(em, name, value, getDomainClass(), kClass);
    }

    @Override
    public <R, K> R findByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass) {
        return FindByParentName.get(em, name, value, getDomainClass(), kClass, column, resultClass);
    }

    @Override
    public <D, K> D findByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return FindByParentName.get(em, name, value, getDomainClass(), kClass, dtoClass, columns);
    }

    @Override
    public <K> Map<String, ?> findByParentName(String name, Object value, Class<K> kClass, String... columns) {
        return FindByParentName.get(em, name, value, getDomainClass(), kClass, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <K> List<T> findAllByParentName(String name, Object value, Class<K> kClass) {
        return FindByParentName.getList(em, name, value, null, getDomainClass(), kClass);
    }

    @Override
    public <K> List<T> findAllByParentName(String name, Object value, Class<K> kClass, Sort sort) {
        return FindByParentName.getList(em, name, value, sort, getDomainClass(), kClass);
    }

    @Override
    public <K> Page<T> findAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count) {
        return FindByParentName.getPage(em, name, value, pageable, getDomainClass(), kClass, count);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <R, K> List<R> findAllByParentName(String name, Object value, Class<K> kClass, String column, Class<R> resultClass) {
        return FindByParentName.getList(em, name, value, null, getDomainClass(), kClass, column, resultClass);
    }

    @Override
    public <R, K> List<R> findAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String column, Class<R> resultClass) {
        return FindByParentName.getList(em, name, value, sort, getDomainClass(), kClass, column, resultClass);
    }

    @Override
    public <R, K> Page<R> findAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return FindByParentName.getPage(em, name, value, pageable, getDomainClass(), kClass, count, column, resultClass);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <D, K> List<D> findAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, String... columns) {
        return FindByParentName.getList(em, name, value, null, getDomainClass(), kClass, dtoClass, columns);
    }

    @Override
    public <D, K> List<D> findAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Sort sort, String... columns) {
        return FindByParentName.getList(em, name, value, sort, getDomainClass(), kClass, dtoClass, columns);
    }

    @Override
    public <D, K> Page<D> findAllByParentName(String name, Object value, Class<K> kClass, Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return FindByParentName.getPage(em, name, value, pageable, getDomainClass(), kClass, dtoClass, count, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <K> List<Map<String, Object>> findAllByParentName(String name, Object value, Class<K> kClass, String... columns) {
        return FindByParentName.getList(em, name, value, null, getDomainClass(), kClass, columns);
    }

    @Override
    public <K> List<Map<String, Object>> findAllByParentName(String name, Object value, Class<K> kClass, Sort sort, String... columns) {
        return FindByParentName.getList(em, name, value, sort, getDomainClass(), kClass, columns);
    }

    @Override
    public <K> Page<Map<String, Object>> findAllByParentName(String name, Object value, Class<K> kClass, Pageable pageable, boolean count, String... columns) {
        return FindByParentName.getPage(em, name, value, pageable, getDomainClass(), kClass, count, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public List<T> findAll() {
        return FindAll.getList(em, null, getDomainClass());
    }

    @Override
    public List<T> findAll(Sort sort) {
        return FindAll.getList(em, sort, getDomainClass());
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return FindAll.getPage(em, pageable, getDomainClass(), true);
    }

    @Override
    public Page<T> findAll(Pageable pageable, boolean count) {
        return FindAll.getPage(em, pageable, getDomainClass(), count);
    }

    /**
     * Không dùng Search
     */

    @Override
    public List<Map<String, Object>> findAll(String... columns) {
        return FindAll.getList(em, null, getDomainClass(), columns);
    }

    @Override
    public List<Map<String, Object>> findAll(Sort sort, String... columns) {
        return FindAll.getList(em, sort, getDomainClass(), columns);
    }

    @Override
    public Page<Map<String, Object>> findAll(Pageable pageable, boolean count, String... columns) {
        return FindAll.getPage(em, pageable, getDomainClass(), count, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <R> List<R> findAll(String column, Class<R> resultClass) {
        return FindAll.getList(em, null, getDomainClass(), column, resultClass);
    }

    @Override
    public <R> List<R> findAll(Sort sort, String column, Class<R> resultClass) {
        return FindAll.getList(em, sort, getDomainClass(), column, resultClass);
    }

    @Override
    public <R> Page<R> findAll(Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return FindAll.getPage(em, pageable, getDomainClass(), count, column, resultClass);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <D> List<D> findAll(Class<D> dtoClass, String... columns) {
        return FindAll.getList(em, null, getDomainClass(), dtoClass, columns);
    }

    @Override
    public <D> List<D> findAll(Class<D> dtoClass, Sort sort, String... columns) {
        return FindAll.getList(em, sort, getDomainClass(), dtoClass, columns);
    }

    @Override
    public <D> Page<D> findAll(Class<D> dtoClass, Pageable pageable, boolean count, String... columns) {
        return FindAll.getPage(em, pageable, getDomainClass(), dtoClass, count, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public List<T> findAll(WhereSQL whereSQL) {
        return WhereSQLFindAll.getList(em, whereSQL, null, getDomainClass());
    }

    @Override
    public List<T> findAll(WhereSQL whereSQL, Sort sort) {
        return WhereSQLFindAll.getList(em, whereSQL, sort, getDomainClass());
    }

    @Override
    public Page<T> findAll(WhereSQL whereSQL, Pageable pageable, boolean count) {
        return WhereSQLFindAll.getPage(em, whereSQL, pageable, count, getDomainClass());
    }

    /**
     * Không dùng Search
     */

    @Override
    public <R> List<R> findAll(WhereSQL whereSQL, String column, Class<R> resultClass) {
        return WhereSQLFindAll.getList(em, whereSQL, null, getDomainClass(), column, resultClass);
    }

    @Override
    public <R> List<R> findAll(WhereSQL whereSQL, Sort sort, String column, Class<R> resultClass) {
        return WhereSQLFindAll.getList(em, whereSQL, sort, getDomainClass(), column, resultClass);
    }

    @Override
    public <R> Page<R> findAll(WhereSQL whereSQL, Pageable pageable, boolean count, String column, Class<R> resultClass) {
        return WhereSQLFindAll.getPage(em, whereSQL, pageable, count, getDomainClass(), column, resultClass);
    }

    /**
     * Không dùng Search
     */

    @Override
    public <D> List<D> findAll(WhereSQL whereSQL, Class<D> dtoClass, String... columns) {
        return WhereSQLFindAll.getList(em, whereSQL, null, getDomainClass(), dtoClass, columns);
    }

    @Override
    public <D> List<D> findAll(WhereSQL whereSQL, Sort sort, Class<D> dtoClass, String... columns) {
        return WhereSQLFindAll.getList(em, whereSQL, sort, getDomainClass(), dtoClass, columns);
    }

    @Override
    public <D> Page<D> findAll(WhereSQL whereSQL, Pageable pageable, boolean count, Class<D> dtoClass, String... columns) {
        return WhereSQLFindAll.getPage(em, whereSQL, pageable, count, getDomainClass(), dtoClass, columns);
    }

    /**
     * Không dùng Search
     */

    @Override
    public List<Map<String, Object>> findAll(WhereSQL whereSQL, String... columns) {
        return WhereSQLFindAll.getList(em, whereSQL, null, getDomainClass(), columns);
    }

    @Override
    public List<Map<String, Object>> findAll(WhereSQL whereSQL, Sort sort, String... columns) {
        return WhereSQLFindAll.getList(em, whereSQL, sort, getDomainClass(), columns);
    }

    @Override
    public Page<Map<String, Object>> findAll(WhereSQL whereSQL, Pageable pageable, boolean count, String... columns) {
        return WhereSQLFindAll.getPage(em, whereSQL, pageable, count, getDomainClass(), columns);
    }
}