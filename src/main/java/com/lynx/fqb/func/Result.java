package com.lynx.fqb.func;

import java.util.Collection;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public interface Result<A> extends Function<EntityManager, TypedQuery<A>> {

    default Collection<A> getResultList(EntityManager em) {
        return apply(em).getResultList();
    }

    default Collection<A> getResultList(EntityManager em, int offset, int limit) {
        TypedQuery<A> q = apply(em);

        q.setFirstResult(offset);
        q.setMaxResults(limit);

        return q.getResultList();
    }

    default A getResult(EntityManager em) {
        return apply(em).getSingleResult();
    }

}
