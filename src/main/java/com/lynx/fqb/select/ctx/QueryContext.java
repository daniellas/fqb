package com.lynx.fqb.select.ctx;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public interface QueryContext {

    EntityManager getEntityManager();

    default CriteriaBuilder getCriteriaBuilder() {
        return getEntityManager().getCriteriaBuilder();
    }

    <T> void apply(CriteriaQuery<T> criteriaQuery);

}
