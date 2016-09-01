package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Result<R> implements QueryContext, ResultOperations {

    protected final Supplier<Class<R>> resultCls;

    private final QueryContext ctx;

    public Result(QueryContext ctx, Class<R> resultCls) {
        this.ctx = ctx;
        this.resultCls = () -> resultCls;
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls) {
        return null;
    }

    @Override
    public <T> Class<T> getFromCls() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> Root<T> getRoot() {
        throw new UnsupportedOperationException();
    }

    @Override
    public QueryContext getQueryContext() {
        return this;
    }

}
