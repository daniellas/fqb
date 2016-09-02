package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.lynx.fqb.select.ctx.QueryContextSupplier;

public interface Orders<F> extends QueryContextSupplier {

    default OrderByOperations<F> orderBy(Supplier<BiFunction<CriteriaBuilder, Root<?>, List<Order>>> order) {
        return new OrderBy<>(getQueryContext(), Optional.ofNullable(order)
                .map(o -> o.get())
                .orElse(null));
    }

    default OrderByOperations<F> orderBy(BiFunction<CriteriaBuilder, Root<?>, List<Order>> order) {
        return orderBy(() -> order);
    }

}
