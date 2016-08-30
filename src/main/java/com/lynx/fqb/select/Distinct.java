package com.lynx.fqb.select;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

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
    public <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls) {
        return ctx.doApply(fromCls)
                .map(q -> q.distinct(true));
    }

    @Override
    public <T> Class<T> fromCls() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Path<T> root() {
        throw new UnsupportedOperationException();
    }

}
