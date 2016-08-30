package com.lynx.fqb;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import com.lynx.fqb.paging.Pageable;

/**
 * JPA Criteria Builder common operations
 * 
 * @author daniel.las
 *
 */
public interface CriteriaQueryApplier {

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

    default <F> List<F> applyListResult(EntityManager em, CriteriaQuery<F> criteriaQuery, Pageable page) {
        return Optional.of(criteriaQuery)
                .map(q -> createTypedQuery(em, q))
                .map(tq -> applyPaging(tq, page))
                .map(tq -> tq.getResultList())
                .get();
    }

    default <F> F applySingleResult(EntityManager em, CriteriaQuery<F> criteriaQuery) {
        return Optional.of(criteriaQuery).map(q -> createTypedQuery(em, q))
                .map(tq -> tq.getSingleResult())
                .get();
    }

}
