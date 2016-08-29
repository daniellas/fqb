package com.lynx.fqb;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

import com.lynx.fqb.paging.Pageable;

/**
 * JPA Criteria Builder common operations
 * 
 * @author daniel.las
 *
 */
public interface QueryApplier {

    /**
     * Set query root to given fromCls of type F
     * 
     * @param query
     *            {@link CriteriaQuery}
     * @param fromCls
     *            root {@link Class}
     * @return provided,modified {@link CriteriaQuery}
     */
    default <F> Path<F> applyFrom(CriteriaQuery<F> query, Class<F> fromCls) {
        return query.from(fromCls);
    }

    /**
     * Creates typed query
     * 
     * @param em
     *            {@link EntityManager}
     * @param query
     *            {@link CriteriaQuery}
     * @return new {@link TypedQuery}
     */
    default <F> TypedQuery<F> createTypedQuery(EntityManager em, CriteriaQuery<F> query) {
        return em.createQuery(query);
    }

    /**
     * Applies paging by setting first/max results
     * 
     * @param query
     * @param page
     * @return
     */
    default <F> TypedQuery<F> applyPaging(TypedQuery<F> query, Pageable page) {
        return Optional.ofNullable(page)
                .map(p -> query.setFirstResult(page.getOffset()))
                .map(p -> query.setMaxResults(page.getPageSize()))
                .orElse(query);
    }

    default <F> List<F> applyListResult(EntityManager em, Optional<CriteriaQuery<F>> criteriaQuery, Pageable page) {
        return criteriaQuery.map(q -> createTypedQuery(em, q))
                .map(tq -> applyPaging(tq, page))
                .map(tq -> tq.getResultList())
                .orElseThrow(() -> new IllegalStateException("Apply list result failed"));
    }

    default <F> F applySingleResult(EntityManager em, Optional<CriteriaQuery<F>> criteriaQuery) {
        return criteriaQuery.map(q -> createTypedQuery(em, q))
                .map(tq -> tq.getSingleResult())
                .orElseThrow(() -> new IllegalStateException("Apply single result failed"));
    }

}
