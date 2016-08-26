package com.lynx.fqb.api;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

public interface CriteriaBuilderOperations {

    default <F> CriteriaQuery<F> applyFrom(CriteriaQuery<F> query, Class<F> fromCls) {
        query.from(fromCls);

        return query;
    }

    default <F> TypedQuery<F> createTypedQuery(EntityManager em, CriteriaQuery<F> query) {
        return em.createQuery(query);
    }

    default <F> TypedQuery<F> applyPaging(TypedQuery<F> query, Pageable page) {
        return Optional.ofNullable(page)
                .map(p -> query.setFirstResult(page.getOffset()))
                .map(p -> query.setMaxResults(page.getPageSize()))
                .orElse(query);
    }

}
