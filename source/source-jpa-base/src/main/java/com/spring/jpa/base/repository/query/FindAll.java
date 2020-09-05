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
public class FindAll {

    private <T> String getQuery(Class<T> t) {
        return "from " + QueryUtils.GetNameEntity(t);
    }

    private <T> Query getCountQuery(EntityManager e, Class<T> t) {
        return e.createQuery(QueryUtils.queryCount(getQuery(t)));
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================
    public <T> List<T> getList(EntityManager e, Sort s, Class<T> t) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), s), t));
    }

    public <T> Page<T> getPage(EntityManager e, Pageable p, Class<T> t, boolean c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), p), t), p), getCountQuery(e, t), c);
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <T, R> List<R> getList(EntityManager e, Sort s, Class<T> t, String c, Class<R> r) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), s, c), r));
    }

    public <T, R> Page<R> getPage(EntityManager e, Pageable p, Class<T> t, boolean i, String c, Class<R> r) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), p, c), r), p), getCountQuery(e, t), i);
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <T, D> List<D> getList(EntityManager e, Sort s, Class<T> t, Class<D> d, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), s, d, c), d));
    }

    public <T, D> Page<D> getPage(EntityManager e, Pageable p, Class<T> t, Class<D> d, boolean i, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), p, d, c), d), p), getCountQuery(e, t), i);
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <T> List<Map<String, Object>> getList(EntityManager e, Sort s, Class<T> t, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), s, c)), c);
    }

    public <T> Page<Map<String, Object>> getPage(EntityManager e, Pageable p, Class<T> t, boolean i, String... c) {
        return EMUtils.pageResult(e, QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(t), p, c)), p), getCountQuery(e, t), i, c);
    }
}