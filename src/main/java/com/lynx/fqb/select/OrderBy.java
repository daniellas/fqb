package com.lynx.fqb.select;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;

public class OrderBy<F> implements CriteriaQueryApplier, ListResults<F>, SingleResults<F> {

    final From<F> from;

    final Stream<BiFunction<CriteriaBuilder, Path<?>, Order>> orders;

    public OrderBy(From<F> from, Stream<BiFunction<CriteriaBuilder, Path<?>, Order>> orders) {
        this.from = from;
        this.orders = orders;
    }

    @Override
    public List<F> apply(Pageable page, boolean distinct) {
        return applyListResult(from.getEntityManager(),
                from.doApply()
                        .map(q -> applyDistinct(q, distinct))
                        .map(
                                q -> q.orderBy(orders
                                        .map(o -> o.apply(from.getEntityManager().getCriteriaBuilder(), from.getRoot()))
                                        .collect(Collectors.toList()))),
                page);
    }

    @Override
    public F get() {
        return from.get();
    }

}
