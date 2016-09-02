package com.lynx.fqb.select;

import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

import com.lynx.fqb.path.MultiplePathProvider;
import com.lynx.fqb.select.ctx.QueryContext;

public class Result<R> implements QueryContext, ResultOperations {

    private final Supplier<Class<R>> resultCls;

    private final Supplier<MultiplePathProvider> paths;

    private final QueryContext ctx;

    public Result(QueryContext ctx, Supplier<Class<R>> resultCls, Supplier<MultiplePathProvider> paths) {
        this.ctx = ctx;
        this.resultCls = resultCls;
        this.paths = paths;
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public QueryContext getQueryContext() {
        return this;
    }

    @Override
    public <T> void apply(CriteriaQuery<T> criteriaQuery) {
        
    }

}
