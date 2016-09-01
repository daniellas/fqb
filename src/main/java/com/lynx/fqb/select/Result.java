package com.lynx.fqb.select;

import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lynx.fqb.path.MultiplePathApplier;

public class Result<R> implements QueryContext, ResultOperations {

    private final Supplier<Class<R>> resultCls;

    private final Supplier<MultiplePathApplier> paths;

    private final QueryContext ctx;

    public Result(QueryContext ctx, Supplier<Class<R>> resultCls, Supplier<MultiplePathApplier> paths) {
        this.ctx = ctx;
        this.resultCls = resultCls;
        this.paths = paths;
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

    @Override
    public <T> Class<T> getResultCls() {
        // TODO Auto-generated method stub
        return null;
    }

}
