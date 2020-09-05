package com.spring.jpa.base.repository.query;

import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.sql.WhereSQL;
import com.spring.jpa.base.utils.ParamsSQLUtils;
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
public class WhereSQLFindAll {

    private <T> String getQuery(WhereSQL w, Class<T> t) {
        return "from " + QueryUtils.GetNameEntity(t) + " where " + w.getSqlWhere();
    }

    private <T> Query getQueryCount(EntityManager e, WhereSQL w, Class<T> t) {
        return ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.queryCount(getQuery(w, t))), w.getParamsSQLS());
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================
    public <T> List<T> getList(EntityManager e, WhereSQL w, Sort s, Class<T> t) {
        return EMUtils.listResult(e, ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), s), t), w.getParamsSQLS()));
    }

    public <T> Page<T> getPage(EntityManager e, WhereSQL w, Pageable p, boolean c, Class<T> t) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), p), t), w.getParamsSQLS()), p),
                getQueryCount(e, w, t), c);
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <T, R> List<R> getList(EntityManager e, WhereSQL w, Sort s, Class<T> t, String c, Class<R> r) {
        return EMUtils.listResult(e, ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), s, c), r), w.getParamsSQLS()));
    }

    public <T, R> Page<R> getPage(EntityManager e, WhereSQL w, Pageable p, boolean i, Class<T> t, String c, Class<R> r) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), p, c), r), w.getParamsSQLS()), p),
                getQueryCount(e, w, t), i);
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <T, D> List<D> getList(EntityManager e, WhereSQL w, Sort s, Class<T> t, Class<D> d, String... c) {
        return EMUtils.listResult(e, ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), s, d, c), d), w.getParamsSQLS()));
    }

    public <T, D> Page<D> getPage(EntityManager e, WhereSQL w, Pageable p, boolean i, Class<T> t, Class<D> d, String... c) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), p, d, c), d), w.getParamsSQLS()), p),
                getQueryCount(e, w, t), i);
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <T> List<Map<String, Object>> getList(EntityManager e, WhereSQL w, Sort s, Class<T> t, String... c) {
        return EMUtils.listResult(e, ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), s, c)), w.getParamsSQLS()), c);
    }

    public <T> Page<Map<String, Object>> getPage(EntityManager e, WhereSQL w, Pageable p, boolean i, Class<T> t, String... c) {
        return EMUtils.pageResult(e,
                QueryUtils.setPageable(ParamsSQLUtils.setParameterForQuery(e.createQuery(QueryUtils.generateStringQuerySort(getQuery(w, t), p, c)), w.getParamsSQLS()), p),
                getQueryCount(e, w, t), i, c);
    }
}