package com.lynx.fqb;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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

    default Optional<T> getSingleResult(EntityManager em) {
        try {
            return Optional.of(apply(em).getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
