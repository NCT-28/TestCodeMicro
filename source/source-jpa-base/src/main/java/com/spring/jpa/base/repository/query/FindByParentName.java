package com.spring.jpa.base.repository.query;

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

@UtilityClass
public class FindByParentName {

    private <T, K> String getQuery(String n, Class<T> t, Class<K> k) {
        return "from " + QueryUtils.GetNameEntity(t) + " where " + QueryUtils.GetFieldNameByTypeClass(t, k) + "." + n + "=:p";
    }

    private <T, K> Query getCountQuery(EntityManager e, String n, Object v, Class<T> t, Class<K> k) {
        return e.createQuery(QueryUtils.queryCount(getQuery(n, t, k))).setParameter("p", v);
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================
    public <T, K> T get(EntityManager e, String n, Object v, Class<T> t, Class<K> k) {
        return EMUtils.singleResult(e, e.createQuery(getQuery(n, t, k), t).setParameter("p", v));
    }

    public <T, K> List<T> getList(EntityManager e, String n, Object v, Sort s, Class<T> t, Class<K> k) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), s), t).setParameter("p", v));
    }

    public <T, K> Page<T> getPage(EntityManager e, String n, Object v, Pageable p, Class<T> t, Class<K> k, boolean l) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), p), t).setParameter("p", v), p),
                getCountQuery(e, n, v, t, k), l);
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <T, K, R> R get(EntityManager e, String n, Object v, Class<T> t, Class<K> k, String c, Class<R> r) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(c) + getQuery(n, t, k), r).setParameter("p", v));
    }

    public <T, K, R> List<R> getList(EntityManager e, String n, Object v, Sort s, Class<T> t, Class<K> k, String c, Class<R> r) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), s, c), r).setParameter("p", v));
    }

    public <T, K, R> Page<R> getPage(EntityManager e, String n, Object v, Pageable p, Class<T> t, Class<K> k, boolean l, String c, Class<R> r) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), p, c), r).setParameter("p", v), p),
                getCountQuery(e, n, v, t, k), l);
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <T, K, D> D get(EntityManager e, String n, Object v, Class<T> t, Class<K> k, Class<D> d, String... c) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(d, c) + getQuery(n, t, k), d).setParameter("p", v));
    }

    public <T, K, D> List<D> getList(EntityManager e, String n, Object v, Sort s, Class<T> t, Class<K> k, Class<D> d, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), s, d, c), d).setParameter("p", v));
    }

    public <T, K, D> Page<D> getPage(EntityManager e, String n, Object v, Pageable p, Class<T> t, Class<K> k, Class<D> d, boolean l, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), p, d, c), d).setParameter("p", v), p),
                getCountQuery(e, n, v, t, k), l);
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <T, K> Map<String, ?> get(EntityManager e, String n, Object v, Class<T> t, Class<K> k, String... c) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(c) + getQuery(n, t, k)).setParameter("p", v), c);
    }

    public <T, K> List<Map<String, Object>> getList(EntityManager e, String n, Object v, Sort s, Class<T> t, Class<K> k, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), s, c)).setParameter("p", v), c);
    }

    public <T, K> Page<Map<String, Object>> getPage(EntityManager e, String n, Object v, Pageable p, Class<T> t, Class<K> k, boolean l, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t, k), p, c)).setParameter("p", v), p),
                getCountQuery(e, n, v, t, k), l, c);
    }
}