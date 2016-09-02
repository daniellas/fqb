package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;
import com.lynx.fqb.select.ctx.QueryContext;
import com.lynx.fqb.select.ctx.SourceContext;

public class From<F> implements SourceContext<F>, CriteriaQueryApplier, FromOperations<F> {

    private final Supplier<Class<F>> fromCls;

    private final QueryContext ctx;

    private Root<F> root;

    public From(QueryContext ctx, Supplier<Class<F>> fromCls) {
        this.ctx = ctx;
        this.fromCls = fromCls;
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
    public List<F> apply(Pageable page) {
        return doApply()
                .map(q -> applyListResult(ctx.getEntityManager(), q, page))
                .get();
    }

    @Override
    public F get() {
        return doApply()
                .map(q -> applySingleResult(ctx.getEntityManager(), q))
                .get();
    }

    @Override
    public Root<F> getRoot() {
        return root;
    }

    @Override
    public <T> void apply(CriteriaQuery<T> criteriaQuery) {
    }

    @Override
    public Optional<CriteriaQuery<F>> doApply() {
        return Optional.of(getCriteriaBuilder().createQuery(fromCls.get())).map(q -> {
            ctx.apply(q);
            root = q.from(fromCls.get());

            return q;
        });
    }

}
