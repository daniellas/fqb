package com.lynx.fqb.select;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.lynx.fqb.select.ctx.QueryContext;

public class Distinct implements QueryContext, Sources {

    private final QueryContext ctx;

    public Distinct(QueryContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public QueryContext getQueryContext() {
        return this;
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public <T> void apply(CriteriaQuery<T> criteriaQuery) {
        criteriaQuery.distinct(true);
    }

}
