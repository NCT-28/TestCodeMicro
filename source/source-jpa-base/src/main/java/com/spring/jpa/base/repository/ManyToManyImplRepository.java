package com.spring.jpa.base.repository;

import com.spring.entity.base.BaseEntity;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.repository.query.FindJoinTableByID;
import com.spring.jpa.base.repository.query.FindJoinTableByName;
import com.spring.jpa.base.repository.update.UpdateById;
import com.spring.jpa.base.utils.QueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@NoRepositoryBean
public abstract class ManyToManyImplRepository<T extends BaseEntity, TS extends BaseEntity, J extends BaseEntity>
        extends SimpleJpaRepository<TS, UUID> implements ManyToManyRepository<T, TS, J, UUID> {

    private final EntityManager em;

    private final BaseRepository<T, UUID> tRepository;
    private final BaseRepository<J, UUID> jRepository;

    private final Class<T> tClass;
    private final Class<J> jClass;

    private String tClassID;
    private String jClassID;

    protected ManyToManyImplRepository(Class<T> t, Class<TS> ts, Class<J> j, EntityManager em, BaseRepository<T, UUID> tR, BaseRepository<J, UUID> jR) {
        super(ts, em);
        this.em = em;
        this.tClass = t;
        this.jClass = j;
        this.tRepository = tR;
        this.jRepository = jR;
        for (Field field : ts.getDeclaredFields()) {
            if (field.getType().equals(t) && field.isAnnotationPresent(JoinColumn.class) && field.isAnnotationPresent(ManyToOne.class)) {
                this.tClassID = field.getName();
            }
            if (field.getType().equals(j) && field.isAnnotationPresent(JoinColumn.class) && field.isAnnotationPresent(ManyToOne.class)) {
                this.jClassID = field.getName();
            }
        }
    }

    public EntityManager getEM() {
        return this.em;
    }

    protected Class<T> getTClass() {
        return this.tClass;
    }

    protected Class<J> getJClass() {
        return this.jClass;
    }

    protected String getTClassID() {
        return this.tClassID;
    }

    protected String getJClassID() {
        return this.jClassID;
    }

    protected String getQuery(String paramDomain, String paramJ) {
        return "from " + QueryUtils.GetNameEntity(getDomainClass()) + " " + paramDomain + " join " + QueryUtils.GetNameEntity(getJClass()) + " " + paramJ +
                " on " + paramJ + "." + baseIDName + "=" + paramDomain + "." + getJClassID() + "." + baseIDName;
    }

    public String getQuery(String paramDomain, String paramT, String paramJ) {
        return getQuery(paramDomain, paramJ) + " join " + QueryUtils.GetNameEntity(getTClass()) + " " + paramT +
                " on " + paramT + "." + baseIDName + "=" + paramDomain + "." + getTClassID() + "." + baseIDName + " ";
    }

    @Override
    public T saveEntityWithAllJoinTable(T entity, Iterable<J> entities) {
        T t = tRepository.saveAndFlush(entity);
        jRepository.deleteAll(this.findAllJoinTableByID(entity.getUuid()));
        List<TS> l = new ArrayList<>();
        for (J v : jRepository.saveAll(entities)) {
            l.add(this.creareObject(t, v));
        }
        this.saveAll(l);
        return t;
    }

    @Override
    public T saveEntityWithAllJoinTableSaved(T entity, Iterable<J> entities) {
        T tValue = tRepository.saveAndFlush(entity);
        for (J jValue : entities) {
            this.save(Objects.requireNonNull(this.creareObject(tValue, jValue)));
        }
        return tValue;
    }

    @Override
    public T saveEntityWithAllJoinTableUnique(T entity, Iterable<J> entities, String name, String value) {
        T tValue = tRepository.saveAndFlush(entity);
        Set<J> entitiesUnique = jRepository.saveAllMergeUnique(entities, name, value);
        if (entitiesUnique == null) {
            return null;
        }
        for (J jValue : entitiesUnique) {
            this.save(Objects.requireNonNull(this.creareObject(tValue, jValue)));
        }
        return tValue;
    }

    private TS creareObject(T tEntity, J jEntity) {
        try {
            Constructor<TS> ctor = getDomainClass().getDeclaredConstructor(this.tClass, this.jClass);
            ctor.setAccessible(true);
            return ctor.newInstance(tEntity, jEntity);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException x) {
            x.printStackTrace();
        }
        return null;
    }

    /**
     * Cập nhật dữ liệu theo trường cố định
     *
     * @param params danh sách các cột cần cập nhật
     * @param uuid   id cần cập nhật
     * @return true: thành công  /  false: không thành công
     */
    @Override
    public boolean updateById(Map<String, Object> params, UUID uuid) {
        return EMUtils.execute(em, UpdateById.generateQuery(em, getDomainClass(), params, uuid));
    }

    /**
     * With name column
     */

    @Override
    public List<J> findAllJoinTableByID(UUID id) {
        return FindJoinTableByID.getList(em, id, null, tClassID, jClassID, getDomainClass(), jClass);
    }

    @Override
    public List<J> findAllJoinTableByID(UUID id, Sort sort) {
        return FindJoinTableByID.getList(em, id, sort, tClassID, jClassID, getDomainClass(), jClass);
    }

    @Override
    public Page<J> findAllJoinTableByID(UUID id, Pageable pageable, boolean count) {
        return FindJoinTableByID.getPage(em, id, pageable, count, tClassID, jClassID, getDomainClass(), jClass);
    }

    /**
     * With name column
     */

    @Override
    public <R> List<R> findAllJoinTableByID(UUID id, String column, Class<R> rClass) {
        return FindJoinTableByID.getList(em, id, null, tClassID, jClassID, getDomainClass(), jClass, column, rClass);
    }

    @Override
    public <R> List<R> findAllJoinTableByID(UUID id, Sort sort, String column, Class<R> rClass) {
        return FindJoinTableByID.getList(em, id, sort, tClassID, jClassID, getDomainClass(), jClass, column, rClass);
    }

    @Override
    public <R> Page<R> findAllJoinTableByID(UUID id, Pageable pageable, boolean count, String column, Class<R> rClass) {
        return FindJoinTableByID.getPage(em, id, pageable, count, tClassID, jClassID, getDomainClass(), jClass, column, rClass);
    }

    /**
     * With name column
     */

    @Override
    public <D> List<D> findAllJoinTableByID(UUID id, Class<D> dClass, String... columns) {
        return FindJoinTableByID.getList(em, id, null, tClassID, jClassID, dClass, getDomainClass(), jClass, columns);
    }

    @Override
    public <D> List<D> findAllJoinTableByID(UUID id, Class<D> dClass, Sort sort, String... columns) {
        return FindJoinTableByID.getList(em, id, sort, tClassID, jClassID, dClass, getDomainClass(), jClass, columns);
    }

    @Override
    public <D> Page<D> findAllJoinTableByID(UUID id, Class<D> dClass, Pageable pageable, boolean count, String... columns) {
        return FindJoinTableByID.getPage(em, id, pageable, count, dClass, getDomainClass(), jClass, tClassID, jClassID, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<Map<String, Object>> findAllJoinTableByID(UUID id, String... columns) {
        return FindJoinTableByID.getList(em, id, null, tClassID, jClassID, getDomainClass(), jClass, columns);
    }

    @Override
    public List<Map<String, Object>> findAllJoinTableByID(UUID id, Sort sort, String... columns) {
        return FindJoinTableByID.getList(em, id, sort, tClassID, jClassID, getDomainClass(), jClass, columns);
    }

    @Override
    public Page<Map<String, Object>> findAllJoinTableByID(UUID id, Pageable pageable, boolean count, String... columns) {
        return FindJoinTableByID.getPage(em, id, pageable, count, tClassID, jClassID, getDomainClass(), jClass, columns);
    }

    /**
     * With name column =============================================================================================================
     * With name column =============================================================================================================
     * With name column =============================================================================================================
     */

    @Override
    public List<J> findAllJoinTableByName(String name, Object value) {
        return FindJoinTableByName.getList(em, name, value, null, tClassID, jClassID, getDomainClass(), jClass);
    }

    @Override
    public List<J> findAllJoinTableByName(String name, Object value, Sort sort) {
        return FindJoinTableByName.getList(em, name, value, sort, tClassID, jClassID, getDomainClass(), jClass);
    }

    @Override
    public Page<J> findAllJoinTableByName(String name, Object value, Pageable pageable, boolean count) {
        return FindJoinTableByName.getPage(em, name, value, pageable, count, tClassID, jClassID, getDomainClass(), jClass);
    }

    /**
     * With name column
     */

    @Override
    public <R> List<R> findAllJoinTableByName(String name, Object value, String column, Class<R> rClass) {
        return FindJoinTableByName.getList(em, name, value, null, tClassID, jClassID, getDomainClass(), jClass, column, rClass);
    }

    @Override
    public <R> List<R> findAllJoinTableByName(String name, Object value, Sort sort, String column, Class<R> rClass) {
        return FindJoinTableByName.getList(em, name, value, sort, tClassID, jClassID, getDomainClass(), jClass, column, rClass);
    }

    @Override
    public <R> Page<R> findAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String column, Class<R> rClass) {
        return FindJoinTableByName.getPage(em, name, value, pageable, count, tClassID, jClassID, getDomainClass(), jClass, column, rClass);
    }

    /**
     * With name column
     */

    @Override
    public <D> List<D> findAllJoinTableByName(String name, Object value, Class<D> dClass, String... columns) {
        return FindJoinTableByName.getList(em, name, value, null, tClassID, jClassID, dClass, getDomainClass(), jClass, columns);
    }

    @Override
    public <D> List<D> findAllJoinTableByName(String name, Object value, Class<D> dClass, Sort sort, String... columns) {
        return FindJoinTableByName.getList(em, name, value, sort, tClassID, jClassID, dClass, getDomainClass(), jClass, columns);
    }

    @Override
    public <D> Page<D> findAllJoinTableByName(String name, Object value, Class<D> dClass, Pageable pageable, boolean count, String... columns) {
        return FindJoinTableByName.getPage(em, name, value, pageable, count, dClass, getDomainClass(), jClass, tClassID, jClassID, columns);
    }

    /**
     * With name column
     */

    @Override
    public List<Map<String, Object>> findAllJoinTableByName(String name, Object value, String... columns) {
        return FindJoinTableByName.getList(em, name, value, null, tClassID, jClassID, getDomainClass(), jClass, columns);
    }

    @Override
    public List<Map<String, Object>> findAllJoinTableByName(String name, Object value, Sort sort, String... columns) {
        return FindJoinTableByName.getList(em, name, value, sort, tClassID, jClassID, getDomainClass(), jClass, columns);
    }

    @Override
    public Page<Map<String, Object>> findAllJoinTableByName(String name, Object value, Pageable pageable, boolean count, String... columns) {
        return FindJoinTableByName.getPage(em, name, value, pageable, count, tClassID, jClassID, getDomainClass(), jClass, columns);
    }
}