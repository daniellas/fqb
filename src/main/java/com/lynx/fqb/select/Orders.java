package com.lynx.fqb.select;

import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

public interface Orders<F> extends QueryContextSupplier, ListResults<F>, SingleResults<F> {

    default <A> OrderBy<F> orderBy(BiFunction<CriteriaBuilder, Path<?>, List<Order>> order) {
        return new OrderBy<>(getQueryContext(), order);
    }

}
