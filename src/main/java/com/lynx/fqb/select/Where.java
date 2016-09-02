package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;
import com.lynx.fqb.select.ctx.SourceContext;

public class Where<R, F> implements SourceContext<F>, CriteriaQueryApplier, WhereOperations<R, F> {

    private final SourceContext<F> ctx;

    public Where(SourceContext<F> ctx) {
        this.ctx = ctx;
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public List<R> apply(Pageable page) {
        return null;
    }

    @Override
    public R get() {
        return null;
    }

    @Override
    public <T> void doApply(CriteriaQuery<T> criteriaQuery) {

    }

    @Override
    public SourceContext<F> getSourceContext() {
        return this;
    }

    @Override
    public Root<F> getRoot() {
        return ctx.getRoot();
    }

    @Override
    public Optional<CriteriaQuery<F>> doApply() {
        return null;
    }

}
