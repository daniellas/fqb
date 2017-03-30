package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;
import com.lynx.fqb.select.ctx.SourceContext;

public class OrderBy<R, F> implements SourceContext<F>, CriteriaQueryApplier, OrderByOperations<F> {

    private final SourceContext<F> ctx;

    private final BiFunction<CriteriaBuilder, Root<?>, List<Order>> orders;

    public OrderBy(SourceContext<F> ctx, BiFunction<CriteriaBuilder, Root<?>, List<Order>> orders) {
        this.ctx = ctx;
        this.orders = orders;
    }

    @Override
    public EntityManager getEntityManager() {
        return ctx.getEntityManager();
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
    public <T> void doApply(CriteriaQuery<T> criteriaQuery) {

    }

    @Override
    public Root<F> getRoot() {
        return ctx.getRoot();
    }

    @Override
    public Optional<CriteriaQuery<F>> doApply() {
        return ctx.doApply()
                .map(q -> {
                    return Optional.ofNullable(orders)
                            .map(o -> q.orderBy(orders.apply(ctx.getCriteriaBuilder(), getRoot())))
                            .orElse(q);
                });
    }

}
