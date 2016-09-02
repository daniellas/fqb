package com.lynx.fqb.select;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.lynx.fqb.select.ctx.QueryContext;

public class Select implements QueryContext, SelectOperations {

    private final EntityManager em;

    private Select(EntityManager em) {
        this.em = em;
    }

    public static SelectOperations using(EntityManager em) {
        return new Select(Optional.ofNullable(em).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    @Override
    public QueryContext getQueryContext() {
        return this;
    }

    @Override
    public <T> void apply(CriteriaQuery<T> criteriaQuery) {
        
    }

}
