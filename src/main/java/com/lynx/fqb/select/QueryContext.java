package com.lynx.fqb.select;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

public interface QueryContext {

    EntityManager getEntityManager();

    default CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls);

    <T> Class<T> getFromCls();

    <T> Path<T> getRoot();
}
