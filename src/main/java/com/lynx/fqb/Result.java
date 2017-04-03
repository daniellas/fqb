package com.lynx.fqb;

import java.util.List;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public interface Result<T> extends Function<EntityManager, TypedQuery<T>> {

    default List<T> getResultList(EntityManager em) {
        return apply(em).getResultList();
    }

    default List<T> getResultList(EntityManager em, int offset, int limit) {
        TypedQuery<T> q = apply(em);

        q.setFirstResult(offset);
        q.setMaxResults(limit);

        return q.getResultList();
    }

    default T getSingleResult(EntityManager em) {
        return apply(em).getSingleResult();
    }

}
