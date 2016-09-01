package com.lynx.fqb.select;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

public interface Orders<F> extends QueryContextSupplier {

    default OrderByOperations<F> orderBy(BiFunction<CriteriaBuilder, Path<?>, List<Order>> order) {
        return orderBy(() -> order);
    }

    default OrderByOperations<F> orderBy(Supplier<BiFunction<CriteriaBuilder, Path<?>, List<Order>>> order) {
        return new OrderBy<>(getQueryContext(), order.get());
    }

}
