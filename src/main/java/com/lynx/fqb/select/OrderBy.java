package com.lynx.fqb.select;

import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;
import com.lynx.fqb.select.ctx.QueryContext;

public class OrderBy<F> implements QueryContext, CriteriaQueryApplier, OrderByOperations<F> {

    private final QueryContext ctx;

    private final BiFunction<CriteriaBuilder, Root<?>, List<Order>> orders;

    public OrderBy(QueryContext ctx, BiFunction<CriteriaBuilder, Root<?>, List<Order>> orders) {
        this.ctx = ctx;
        this.orders = orders;
    }

    // @SuppressWarnings("unchecked")
    // @Override
    // public List<F> apply(Pageable page) {
    // return (List<F>) doApply(ctx.getFromCls())
    // .map(q -> applyListResult(ctx.getEntityManager(), q, page))
    // .get();
    // }
    //
    // @SuppressWarnings("unchecked")
    // @Override
    // public F get() {
    // return (F) ctx.doApply(ctx.getFromCls())
    // .map(q -> applySingleResult(ctx.getEntityManager(), q))
    // .get();
    // }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
    }

    @Override
    public List<F> apply(Pageable page) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public F get() {
        return null;
    }

    @Override
    public <T> void apply(CriteriaQuery<T> criteriaQuery) {

    }

    // @Override
    // public <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls) {
    // return ctx.doApply(fromCls)
    // .map(q -> {
    // return Optional.ofNullable(orders)
    // .map(o -> q.orderBy(orders.apply(ctx.getCriteriaBuilder(), getRoot())))
    // .orElse(q);
    // });
    // }

}
