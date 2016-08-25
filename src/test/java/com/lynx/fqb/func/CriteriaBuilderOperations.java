package com.lynx.fqb.func;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public interface CriteriaBuilderOperations {
    default <FROM> CriteriaQuery<FROM> applyFrom(CriteriaQuery<FROM> query, Class<FROM> fromCls) {
        query.from(fromCls);

        return query;
    }

    default <FROM> TypedQuery<FROM> createTypedQuery(EntityManager em, CriteriaQuery<FROM> query) {
        return em.createQuery(query);
    }

    default <FROM> TypedQuery<FROM> applyFirst(TypedQuery<FROM> query, Integer first) {
        return Optional.ofNullable(first)
                .map(v -> query.setFirstResult(v.intValue()))
                .orElse(query);
    }

    default <FROM> TypedQuery<FROM> applyMax(TypedQuery<FROM> query, Integer max) {
        return Optional.ofNullable(max)
                .map(v -> query.setMaxResults(max))
                .orElse(query);
    }

}
