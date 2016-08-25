package com.lynx.fqb.func;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

public class SelectFrom<FROM> extends Select implements CriteriaBuilderOperations, Results<FROM> {

    protected final Class<FROM> fromCls;

    public SelectFrom(EntityManager em) {
        super(em);
        this.fromCls = null;
    }

    public SelectFrom(EntityManager em, Class<FROM> fromCls) {
        super(em);
        this.fromCls = fromCls;
    }

    @Override
    public List<FROM> apply(Integer first, Integer max) {
        return Optional.of(em.getCriteriaBuilder().createQuery(fromCls))
                .map(q -> applyFrom(q, fromCls))
                .map(q -> createTypedQuery(em, q))
                .map(tq -> applyFirst(tq, first))
                .map(tq -> applyMax(tq, max))
                .map(tq -> tq.getResultList())
                .orElse(null);
    }

}