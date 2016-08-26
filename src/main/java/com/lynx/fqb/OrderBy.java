package com.lynx.fqb;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import com.lynx.fqb.paging.Pageable;

public class OrderBy<F> implements CriteriaBuilderOperations, ListResults<F>, SingleResults<F> {

    final From<F> from;

    final Stream<BiFunction<CriteriaBuilder, Path<F>, Order>> orders;

    public OrderBy(From<F> from, Stream<BiFunction<CriteriaBuilder, Path<F>, Order>> orders) {
        this.from = from;
        this.orders = orders;
    }

    @Override
    public List<F> apply(Pageable page) {
        return applyListResult(from.getEntityManager(),
                from.doApply()
                        .map(
                                q -> q.orderBy(orders
                                        .map(o -> o.apply(from.getEntityManager().getCriteriaBuilder(), from.fromPath))
                                        .collect(Collectors.toList()))),
                page);
    }

    @Override
    public F get() {
        return from.get();
    }

}
