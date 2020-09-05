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
public class FindByName {

    private <T, D> String getQuery(String n, Class<T> t) {
        return "from " + QueryUtils.GetNameEntity(t) + " where " + n + "=:p";
    }

    private <T> Query generateTypedQueryCount(EntityManager e, String n, Object v, Class<T> t) {
        return e.createQuery(QueryUtils.queryCount(getQuery(n, t))).setParameter("p", v);
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================
    public <T> T get(EntityManager e, String n, Object v, Class<T> t) {
        return EMUtils.singleResult(e, e.createQuery(getQuery(n, t), t).setParameter("p", v));
    }

    public <T> List<T> getList(EntityManager e, Sort s, String n, Object v, Class<T> t) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), s), t).setParameter("p", v));
    }

    public <T> Page<T> getPage(EntityManager e, Pageable p, boolean c, String n, Object v, Class<T> t) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), p), t).setParameter(n, v), p),
                generateTypedQueryCount(e, n, v, t), c);
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <T, R> R get(EntityManager e, String n, Object v, Class<T> t, String c, Class<R> r) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(c) + getQuery(n, t), r).setParameter("p", v));
    }

    public <T, R> List<R> getList(EntityManager e, Sort s, String n, Object v, Class<T> t, String c, Class<R> r) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), s, c), r).setParameter("p", v));
    }

    public <T, R> Page<R> getPage(EntityManager e, Pageable p, boolean i, String n, Object v, Class<T> t, String c, Class<R> r) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), p, c), r).setParameter("p", v), p),
                generateTypedQueryCount(e, n, v, t), i);
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <T, D> D get(EntityManager e, String n, Object v, Class<T> t, Class<D> d, String... c) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(d, c) + getQuery(n, t), d).setParameter("p", v));
    }

    public <T, D> List<D> getList(EntityManager e, Sort s, String n, Object v, Class<T> t, Class<D> d, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), s, d, c), d).setParameter("p", v));
    }

    public <T, D> Page<D> getPage(EntityManager e, Pageable p, boolean i, String n, Object v, Class<T> t, Class<D> d, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), p, d, c), d).setParameter("p", v), p),
                generateTypedQueryCount(e, n, v, t), i);
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <T> Map<String, ?> get(EntityManager e, String n, Object v, Class<T> t, String c) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(c) + getQuery(n, t)).setParameter("p", v), c);
    }

    public <T> Map<String, ?> get(EntityManager e, String n, Object v, Class<T> t, String... c) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect(c) + getQuery(n, t)).setParameter("p", v), c);
    }

    public <T> List<Map<String, Object>> getList(EntityManager e, Sort s, String n, Object v, Class<T> t, String c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), s, c)).setParameter("p", v), c);
    }

    public <T> List<Map<String, Object>> getList(EntityManager e, Sort s, String n, Object v, Class<T> t, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), s, c)).setParameter("p", v), c);
    }

    public <T> Page<Map<String, Object>> getPage(EntityManager e, Pageable p, boolean i, String n, Object v, Class<T> t, String c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), p, c)).setParameter("p", v), p),
                generateTypedQueryCount(e, n, v, t), i, c);
    }

    public <T> Page<Map<String, Object>> getPage(EntityManager e, Pageable p, boolean i, String n, Object v, Class<T> t, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(n, t), p, c)).setParameter("p", v), p),
                generateTypedQueryCount(e, n, v, t), i, c);
    }
}