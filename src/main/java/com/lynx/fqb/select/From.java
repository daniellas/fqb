package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;

public class From<R, F> implements QueryContext, CriteriaQueryApplier, FromOperations<R, F> {

    private final Supplier<Class<F>> fromCls;

    private final QueryContext ctx;

    private Root<F> root;

    public From(QueryContext ctx, Supplier<Class<F>> fromClsSupplier) {
        this.ctx = ctx;
        this.fromCls = fromClsSupplier;
    }

    @Override
    public List<R> apply(Pageable page) {
        // return doApply(fromCls.get())
        // .map(q -> applyListResult(ctx.getEntityManager(), q, page))
        // .get();
        return null;
    }

    @Override
    public R get() {
        // return doApply(fromCls.get())
        // .map(q -> applySingleResult(ctx.getEntityManager(), q))
        // .get();
        return null;
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public QueryContext getQueryContext() {
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls) {
        return ctx.doApply(fromCls)
                .map(q -> {
                    root = (Root<F>) q.from(fromCls);

                    return q;
                });

    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Class<T> getFromCls() {
        return (Class<T>) fromCls.get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> Root<T> getRoot() {
        return (Root<T>) root;
    }

    @Override
    public <T> Class<T> getResultCls() {
        // TODO Auto-generated method stub
        return null;
    }

}
