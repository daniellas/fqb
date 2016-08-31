package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;

public class OrderBy<F> implements QueryContext, CriteriaQueryApplier, ListResults<F>, SingleResults<F> {

    private final QueryContext ctx;

    private final Supplier<BiFunction<CriteriaBuilder, Path<?>, List<Order>>> orders;

    public OrderBy(QueryContext ctx, Supplier<BiFunction<CriteriaBuilder, Path<?>, List<Order>>> orders) {
        this.ctx = ctx;
        this.orders = orders;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<F> apply(Pageable page) {
        return (List<F>) doApply(ctx.getFromCls())
                .map(q -> applyListResult(ctx.getEntityManager(), q, page))
                .get();
    }

    @SuppressWarnings("unchecked")
    @Override
    public F get() {
        return (F) ctx.doApply(ctx.getFromCls())
                .map(q -> applySingleResult(ctx.getEntityManager(), q))
                .get();
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls) {
        return ctx.doApply(fromCls)
                .map(q -> q.orderBy(orders.get().apply(ctx.getEntityManager().getCriteriaBuilder(), getRoot())));
    }

    @Override
    public <T> Class<T> getFromCls() {
        return ctx.getFromCls();
    }

    @Override
    public <T> Path<T> getRoot() {
        return ctx.getRoot();
    }

}
