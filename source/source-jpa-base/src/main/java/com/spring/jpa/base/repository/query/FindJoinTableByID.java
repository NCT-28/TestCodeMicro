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
public class FindJoinTableByID {

    private <TS, J> String getQuery(String t, String j, Class<TS> m, Class<J> n) {
        return "from " + QueryUtils.GetNameEntity(m) + " a join " + QueryUtils.GetNameEntity(n) +
                " b on b." + IRepository.baseIDName + "=a." + j + "." + IRepository.baseIDName + " where a." + t + "." + IRepository.baseIDName + "=:p";
    }

    private <TS, J> Query getQueryCount(EntityManager e, UUID i, String k, String o, Class<TS> t, Class<J> j) {
        return e.createQuery(QueryUtils.queryCount("b", getQuery(k, o, t, j))).setParameter("p", i);
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================

    public <TS, J> List<J> getList(EntityManager e, UUID i, Sort s, String k, String o, Class<TS> t, Class<J> j) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(k, o, t, j), s), j).setParameter("p", i));
    }

    public <TS, J> Page<J> getPage(EntityManager e, UUID i, Pageable p, boolean l, String k, String o, Class<TS> t, Class<J> j) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(k, o, t, j), p), j).setParameter("p", i), p),
                getQueryCount(e, i, k, o, t, j), l);
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <TS, J, R> List<R> getList(EntityManager e, UUID i, Sort s, String k, String o, Class<TS> t, Class<J> j, String c, Class<R> r) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(k, o, t, j), s, c), r).setParameter("p", i));
    }

    public <TS, J, R> Page<R> getPage(EntityManager e, UUID i, Pageable p, boolean l, String k, String o, Class<TS> t, Class<J> j, String c, Class<R> r) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(k, o, t, j), p, c), r).setParameter("p", i), p),
                getQueryCount(e, i, k, o, t, j), l);
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <TS, J, D> List<D> getList(EntityManager e, UUID i, Sort s, String k, String o, Class<D> d, Class<TS> t, Class<J> j, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(k, o, t, j), s, d, c), d).setParameter("p", i));
    }

    public <TS, J, D> Page<D> getPage(EntityManager e, UUID i, Pageable p, boolean l, Class<D> d, Class<TS> t, Class<J> j, String k, String o, String... c) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(k, o, t, j), p, d, c), d).setParameter("p", i), p),
                getQueryCount(e, i, k, o, t, j), l);
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <TS, J> List<Map<String, Object>> getList(EntityManager e, UUID i, Sort s, String t, String m, Class<TS> n, Class<J> j, String... c) {
        return EMUtils.listResult(e, e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(t, m, n, j), s, c)).setParameter("p", i), c);
    }

    public <TS, J> Page<Map<String, Object>> getPage(EntityManager e, UUID i, Pageable p, boolean z, String k, String o, Class<TS> t, Class<J> j, String... c) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(e.createQuery(QueryUtils.generateStringQuerySort("b", getQuery(k, o, t, j), p, c)).setParameter("p", i), p),
                getQueryCount(e, i, k, o, t, j), z, c);
    }
}