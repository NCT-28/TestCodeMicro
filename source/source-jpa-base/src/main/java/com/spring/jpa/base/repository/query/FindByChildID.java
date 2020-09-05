package com.spring.jpa.base.repository.query;

import com.spring.jpa.base.repository.IRepository;
import com.spring.jpa.base.repository.manager.EMUtils;
import com.spring.jpa.base.utils.QueryUtils;
import lombok.experimental.UtilityClass;

import javax.persistence.EntityManager;
import java.util.Map;
import java.util.UUID;

@UtilityClass
public class FindByChildID {

    private <T, C> String generateString(Class<T> t, Class<C> c) {
        return "from " + QueryUtils.GetNameEntity(t) + " t join " + QueryUtils.GetNameEntity(c) + " c on c." +
                QueryUtils.GetFieldNameByTypeClass(c, t) + "." + IRepository.baseIDName + "=t." + IRepository.baseIDName + " where c." + IRepository.baseIDName + "=:p";
    }

    // ================================== [] ==================================
    // ================================== [] ==================================
    // ================================== [] ==================================
    public <T, C> T get(EntityManager e, UUID i, Class<T> t, Class<C> c) {
        return EMUtils.singleResult(e, e.createQuery("select t " + generateString(t, c), t).setParameter("p", i));
    }

    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    // ================================== [RESULT] ==================================
    public <T, C, R> R get(EntityManager e, UUID i, Class<T> t, Class<C> c, String s, Class<R> r) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect("t", s) + generateString(t, c), r).setParameter("p", i));
    }

    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    // ================================== [DTO] ==================================
    public <T, C, D> D get(EntityManager e, UUID i, Class<T> t, Class<C> c, Class<D> d, String... s) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect("t", d, s) + generateString(t, c), d).setParameter("p", i));
    }

    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    // ================================== [MAP] ==================================
    public <T, C> Map<String, ?> get(EntityManager e, UUID i, Class<T> t, Class<C> c, String... s) {
        return EMUtils.singleResult(e, e.createQuery(QueryUtils.querySelect("t", s) + generateString(t, c)).setParameter("p", i), s);
    }
}