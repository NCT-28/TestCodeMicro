package com.spring.jpa.base.repository.query;

import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class FindByParentID {

    private <T, K> String getQuery(Class<T> t, Class<K> k) {
        return "from " + QueryUtils.GetNameEntity(t) + " where " + QueryUtils.GetFieldNameByTypeClass(t, k) + "." + IRepository.baseIDName + "=:p";
    }

    private <T, K> Query getCountQuery(EntityManager e, UUID i, Class<T> t, Class<K> k) {
        return e.createQuery(QueryUtils.queryCount(getQuery(t, k))).setParameter("p", i);
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================
    public <T, K> T get(EntityManager e, UUID i, Class<T> t, Class<K> k) {
        return EMUtils.singleResult(e, e.createQuery(getQuery(t, k), t).setParameter("p", i));
    }

    public <T, K> List<T> getList(EntityManager e, UUID i, Sort s, Class<T> t, Class<K> k) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), s), t).setParameter("p", i));
    }

    public <T, K> Page<T> getPage(EntityManager e, UUID i, Pageable p, Class<T> t, Class<K> k, boolean l) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), p), t).setParameter("p", i), p),
                getCountQuery(e, i, t, k), l);
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <T, K, R> R get(EntityManager e, UUID i, Class<T> t, Class<K> k, String c, Class<R> r) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(c) + getQuery(t, k), r).setParameter("p", i));
    }

    public <T, K, R> List<R> getList(EntityManager e, UUID i, Sort s, Class<T> t, Class<K> k, String c, Class<R> r) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), s, c), r).setParameter("p", i));
    }

    public <T, K, R> Page<R> getPage(EntityManager e, UUID i, Pageable p, Class<T> t, Class<K> k, boolean l, String c, Class<R> r) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), p, c), r).setParameter("p", i), p),
                getCountQuery(e, i, t, k), l);
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <T, K, D> D get(EntityManager e, UUID i, Class<T> t, Class<K> k, Class<D> d, String... c) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(d, c) + getQuery(t, k), d).setParameter("p", i));
    }

    public <T, K, D> List<D> getList(EntityManager e, UUID i, Sort s, Class<T> t, Class<K> k, Class<D> d, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), s, d, c), d).setParameter("p", i));
    }

    public <T, K, D> Page<D> getPage(EntityManager e, UUID i, Pageable p, Class<T> t, Class<K> k, Class<D> d, boolean l, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), p, d, c), d).setParameter("p", i), p),
                getCountQuery(e, i, t, k), l);
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <T, K> Map<String, ?> get(EntityManager e, UUID i, Class<T> t, Class<K> k, String... c) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(c) + getQuery(t, k)).setParameter("p", i), c);
    }

    public <T, K> List<Map<String, Object>> getList(EntityManager e, UUID i, Sort s, Class<T> t, Class<K> k, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), s, c)).setParameter("p", i), c);
    }

    public <T, K> Page<Map<String, Object>> getPage(EntityManager e, UUID i, Pageable p, Class<T> t, Class<K> k, boolean l, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t, k), p, c)).setParameter("p", i), p),
                getCountQuery(e, i, t, k), l, c);
    }
}