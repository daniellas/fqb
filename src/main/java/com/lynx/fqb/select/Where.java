package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;

public class Where<F> implements QueryContext, CriteriaQueryApplier, WhereOperations<F> {

    private final QueryContext ctx;

    public Where(QueryContext ctx) {
        this.ctx = ctx;
    }

    @SuppressWarnings("unchecked")
    @Override
    public F get() {
        return (F) ctx.doApply(ctx.getFromCls())
                .map(q -> applySingleResult(ctx.getEntityManager(), q))
                .get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<F> apply(Pageable page) {
        return (List<F>) doApply(ctx.getFromCls())
                .map(q -> applyListResult(ctx.getEntityManager(), q, page))
                .get();
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls) {
        return ctx.doApply(fromCls);
    }

    @Override
    public <T> Class<T> getFromCls() {
        return ctx.getFromCls();
    }

    @Override
    public <T> Path<T> getRoot() {
        return ctx.getRoot();
    }

    @Override
    public QueryContext getQueryContext() {
        return ctx;
    }

}
