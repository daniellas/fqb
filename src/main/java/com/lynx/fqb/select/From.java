package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;

public class From<F> implements QueryContext, CriteriaQueryApplier, FromOperations<F> {

    private final Supplier<Class<F>> fromCls;

    private final QueryContext ctx;

    private Root<F> root;

    public From(QueryContext ctx, Class<F> fromCls) {
        this.ctx = ctx;
        this.fromCls = () -> fromCls;
    }

    public From(QueryContext ctx, Supplier<Class<F>> fromClsSupplier) {
        this.ctx = ctx;
        this.fromCls = fromClsSupplier;
    }

    @Override
    public List<F> apply(Pageable page) {
        return doApply(fromCls.get())
                .map(q -> applyListResult(ctx.getEntityManager(), q, page))
                .get();
    }

    @Override
    public F get() {
        return doApply(fromCls.get())
                .map(q -> applySingleResult(ctx.getEntityManager(), q))
                .get();
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

}
