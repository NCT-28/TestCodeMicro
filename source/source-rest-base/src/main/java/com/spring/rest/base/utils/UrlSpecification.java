package com.spring.rest.base.utils;

import com.base.enums.Ope;
import com.source.base.controller.search.FindOperation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.UUID;

public class UrlSpecification<T> implements Specification<T> {

    private FindOperation criteria;

    public UrlSpecification(FindOperation criteria) {
        this.criteria = criteria;
    }

    public boolean isFilterNull() {
        return criteria == null;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Path<?> path = root.get(criteria.getKey());
        if (path.getJavaType() == String.class) {
            return toPredicateTypeString(root, builder);
        }
        try {
            if (path.getJavaType() == UUID.class) {
                return toPredicateTypeUUID(root, builder, UUID.fromString(criteria.getValue()));
            }
            if (path.getJavaType() == Integer.class) {
                return toPredicateTypeComparable(root, builder, Integer.parseInt(criteria.getValue()));
            }
            if (path.getJavaType() == Long.class) {
                return toPredicateTypeComparable(root, builder, Long.parseLong(criteria.getValue()));
            }
            if (path.getJavaType() == Float.class) {
                return toPredicateTypeComparable(root, builder, Float.parseFloat(criteria.getValue()));
            }
            if (path.getJavaType() == Double.class) {
                return toPredicateTypeComparable(root, builder, Double.parseDouble(criteria.getValue()));
            }
        } catch (Exception ex) {
            return null;
        }
        return null;
    }

    private Predicate toPredicateTypeUUID(Root<T> root, CriteriaBuilder builder, UUID value) {
        Path<UUID> path = root.get(criteria.getKey());
        if (criteria.getOperation() == Ope.eq) {
            return builder.equal(path, value);
        }
        if (criteria.getOperation() == Ope.ne) {
            return builder.notEqual(path, value);
        }
        return null;
    }

    private Predicate toPredicateTypeString(Root<T> root, CriteriaBuilder builder) {
        Path<String> path = root.get(criteria.getKey());
        if (criteria.getOperation() == Ope.eq) {
            return builder.equal(path, criteria.getValue());
        }
        if (criteria.getOperation() == Ope.ne) {
            return builder.notEqual(path, criteria.getValue());
        }
        if (criteria.getOperation() == Ope.like) {
            return builder.like(path, "%" + criteria.getValue() + "%");
        }
        if (criteria.getOperation() == Ope.notlike) {
            return builder.notLike(path, "%" + criteria.getValue() + "%");
        }
        return null;
    }

    private <K extends Comparable<K>> Predicate toPredicateTypeComparable(Root<T> root, CriteriaBuilder builder, K value) {
        Path<K> path = root.get(criteria.getKey());
        if (criteria.getOperation() == Ope.eq) {
            return builder.equal(path, value);
        }
        if (criteria.getOperation() == Ope.ne) {
            return builder.notEqual(path, value);
        }
        if (criteria.getOperation() == Ope.gt) {
            return builder.greaterThan(path, value);
        }
        if (criteria.getOperation() == Ope.ge) {
            return builder.greaterThanOrEqualTo(path, value);
        }
        if (criteria.getOperation() == Ope.lt) {
            return builder.lessThan(path, value);
        }
        if (criteria.getOperation() == Ope.le) {
            return builder.lessThanOrEqualTo(path, value);
        }
        return null;
    }
}