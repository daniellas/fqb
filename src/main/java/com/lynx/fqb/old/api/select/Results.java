package com.lynx.fqb.old.api.select;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.lynx.fqb.old.api.CriteriaQueryHolder;

public interface Results<R> extends CriteriaQueryHolder<R> {

    default TypedQuery<R> getTypedQuery(EntityManager em) {
        return em.createQuery(getCriteriaQuery());
    }

    default List<R> getResultList(EntityManager em) {
        return getTypedQuery(em).getResultList();
    }

    default R getSingleResult(EntityManager em) {
        return getTypedQuery(em).getSingleResult();
    }

}
