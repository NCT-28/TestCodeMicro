package com.spring.jpa.base.repository.manager;

import com.base.dtos.PageT;
import com.spring.jpa.base.utils.QueryUtils;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@UtilityClass
public class EMUtils {

    // =============================================  [Update]  =============================================
    // =============================================  [Update]  =============================================
    // =============================================  [Update]  =============================================
    // =============================================  [Update]  =============================================
    // =============================================  [Update]  =============================================

    public boolean execute(EntityManager e, Query q) {
        try {
            q.executeUpdate();
            e.flush();
            e.clear();
            e.close();
            return true;
        } catch (Exception ex) {
            e.clear();
            e.close();
            return false;
        }
    }

    // =============================================  [Object]  =============================================
    // =============================================  [Object]  =============================================
    // =============================================  [Object]  =============================================
    // =============================================  [Object]  =============================================
    // =============================================  [Object]  =============================================

    public <T> T singleResult(EntityManager e, TypedQuery<T> q) {
        try {
            return q.setMaxResults(1).getSingleResult();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Object singleResult(EntityManager e, Query q) {
        try {
            return q.setMaxResults(1).getSingleResult();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Map<String, ?> singleResult(EntityManager e, Query q, String c) {
        try {
            return QueryUtils.queryToObject(q.setMaxResults(1), c);
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Map<String, ?> singleResult(EntityManager e, Query q, String... c) {
        try {
            return QueryUtils.queryToObject(q.setMaxResults(1), c);
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    // =============================================  [List]  =============================================
    // =============================================  [List]  =============================================
    // =============================================  [List]  =============================================
    // =============================================  [List]  =============================================
    // =============================================  [List]  =============================================

    public <T> List<T> listResult(EntityManager e, TypedQuery<T> q) {
        try {
            return q.getResultList();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public List<?> listResult(EntityManager e, Query q) {
        try {
            return q.getResultList();
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public List<Map<String, Object>> listResult(EntityManager e, Query q, String c) {
        try {
            return QueryUtils.queryToList(q, c);
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public List<Map<String, Object>> listResult(EntityManager e, Query q, String... c) {
        try {
            return QueryUtils.queryToList(q, c);
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    // =============================================  [Page]  =============================================
    // =============================================  [Page]  =============================================
    // =============================================  [Page]  =============================================
    // =============================================  [Page]  =============================================
    // =============================================  [Page]  =============================================

    public <T> Page<T> pageResult(EntityManager e, TypedQuery<T> q, Query c, boolean i) {
        try {
            if (i) {
                return new PageT<>(q.getResultList(), (long) c.setMaxResults(1).getSingleResult());
            }
            return new PageT<>(q.getResultList());
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Page<?> pageResult(EntityManager e, Query q, Query c, boolean i) {
        try {
            if (i) {
                List<?> r = q.getResultList();
                BigInteger o = (BigInteger) c.setMaxResults(1).getSingleResult();
                return new PageT<>(r, o.longValue());
            }
            List<?> r = q.getResultList();
            return new PageT<>(r);
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Page<Map<String, Object>> pageResult(EntityManager e, Query q, Query c, boolean i, String l) {
        try {
            if (i) {
                return new PageT<>(QueryUtils.queryToList(q, l), (long) c.setMaxResults(1).getSingleResult());
            }
            return new PageT<>(QueryUtils.queryToList(q, l));
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }

    public Page<Map<String, Object>> pageResult(EntityManager e, Query q, Query c, boolean i, String... l) {
        try {
            if (i) {
                return new PageT<>(QueryUtils.queryToList(q, l), (long) c.setMaxResults(1).getSingleResult());
            }
            return new PageT<>(QueryUtils.queryToList(q, l));
        } catch (NoResultException | NonUniqueResultException nre) {
            return null;
        }
    }
}