package com.spring.jpa.base.repository.query;

import com.spring.jpa.base.repository.manager.EMUtils;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

@UtilityClass
public class CustomQueryUtils {

    /**
     * Trả về danh sách các cột cần chọn
     *
     * @param r   query
     * @param c   các cột cần chọn
     * @param <T> loại dữ liệu cần chọn
     * @return danh sách
     */
    public <T> List<Selection<?>> getMultiselect(Root<T> r, String... c) {
        List<Selection<?>> l = new ArrayList<>();
        for (String x : c) {
            l.add(r.get(x.trim()));
        }
        return l;
    }

    /**
     * Đếm số lượng
     *
     * @param cq     {@link CriteriaQuery}
     * @param tClass class cần tham chiếu
     * @param cols   các cột cần chọn
     * @param <T>    loại model
     * @return Root
     */
    public <T> Root<T> countSelect(CriteriaQuery<?> cq, Class<T> tClass, String... cols) {
        Root<T> root = cq.from(tClass);
        if (cols.length == 1) {
            cq.select(root.get(cols[0].trim()));
        } else {
            cq.multiselect(getMultiselect(root, cols));
        }
        return root;
    }

    public <T> TypedQuery<T> getTypeQuery(EntityManager entityManager, Specification<T> specification, Pageable pageable, Class<T> tClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = builder.createQuery(tClass);
        Root<T> root = cq.from(tClass);
        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, cq, builder);
            if (predicate != null) {
                cq.where(predicate);
            }
        }
        if (pageable != null) {
            Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
            if (sort.isSorted()) {
                cq.orderBy(toOrders(sort, root, builder));
            }
            TypedQuery<T> query = entityManager.createQuery(cq);
            if (pageable.isPaged()) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            return query;
        }
        return entityManager.createQuery(cq);
    }

    public <T> Page<T> getPageByTypeQuery(EntityManager entityManager, Specification<T> specification, Pageable pageable, Class<T> tClass, boolean count) {
        return EMUtils.pageResult(
                entityManager,
                getTypeQuery(entityManager, specification, pageable, tClass),
                getCountQuery(entityManager, specification, tClass),
                count
        );
    }

    public <T> TypedQuery<?> getTypeQuery(EntityManager em, Specification<T> specification, Pageable pageable, Sort sort, Class<T> tClass, String... columns) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<?> cq;
        if (columns.length == 1) {
            cq = builder.createQuery(Object.class);
        } else {
            cq = builder.createQuery(Object[].class);
        }
        Root<T> root = countSelect(cq, tClass, columns);
        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, cq, builder);
            if (predicate != null) {
                cq.where(predicate);
            }
        }
        if (pageable == null && sort != null) {
            if (sort.isSorted()) {
                cq.orderBy(toOrders(sort, root, builder));
            }
        }
        if (pageable != null) {
            Sort sortPageable = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
            if (sortPageable.isSorted()) {
                cq.orderBy(toOrders(sortPageable, root, builder));
            }
            TypedQuery<?> query = em.createQuery(cq);
            if (pageable.isPaged()) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            return query;
        }
        return em.createQuery(cq);
    }

    protected <T> TypedQuery<Long> getCountQuery(EntityManager entityManager, Specification<T> specification, Class<T> domainClass) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        Root<T> root = query.from(domainClass);
        if (specification != null) {
            Predicate predicate = specification.toPredicate(root, query, builder);
            if (predicate != null) {
                query.where(predicate);
            }
        }
        if (query.isDistinct()) {
            query.select(builder.countDistinct(root));
        } else {
            query.select(builder.count(root));
        }
        return entityManager.createQuery(query);
    }

    public <T> Page<Map<String, Object>> getPageByTypeQuery(EntityManager entityManager, Specification<T> specification,
                                                            Pageable pageable, Sort sort, Class<T> tClass, boolean count, String... columns) {
        return EMUtils.pageResult(
                entityManager,
                getTypeQuery(entityManager, specification, pageable, sort, tClass, columns),
                getCountQuery(entityManager, specification, tClass),
                count,
                columns
        );
    }
}