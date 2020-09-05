package com.spring.jpa.base.repository.query;

import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;
import java.util.Map;

@UtilityClass
public class FindByChildName {

    private <T, C> String generateString(String n, Class<T> t, Class<C> c) {
        return "from " + QueryUtils.GetNameEntity(t) + " t join " + QueryUtils.GetNameEntity(c) + " c on c." +
                QueryUtils.GetFieldNameByTypeClass(c, t) + "." + IRepository.baseIDName + "=t." + IRepository.baseIDName + " where c." + n + "=:p";
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================
    public <T, C> T get(EntityManager e, String n, Object v, Class<T> t, Class<C> c) {
        return EMUtils.singleResult(e, e.createQuery("select t " + generateString(n, t, c), t).setParameter("p", v));
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <T, C, R> R get(EntityManager e, String n, Object v, Class<T> t, Class<C> c, String s, Class<R> r) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect("t", s) + generateString(n, t, c), r).setParameter("p", v));
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <T, C, D> D get(EntityManager e, String n, Object v, Class<T> t, Class<C> c, Class<D> d, String... s) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect("t", d, s) + generateString(n, t, c), d).setParameter("p", v));
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <T, C> Map<String, ?> get(EntityManager e, String n, Object v, Class<T> t, Class<C> c, String... s) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect("t", s) + generateString(n, t, c)).setParameter("p", v), s);
    }
}