package com.lynx.fqb;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

public interface Orders<F> extends ListResults<F>, SingleResults<F>, FromSupplier<F>, EntityManagerSupplier {

    default <A> OrderBy<F> orderBy(BiFunction<CriteriaBuilder, Path<?>, Order> order) {
        return new OrderBy<>(getFrom(), Stream.of(order));
    }

    default <A> OrderBy<F> orderBy(Collection<BiFunction<CriteriaBuilder, Path<?>, Order>> orders) {
        return orderBy(orders.stream());
    }

    default <A> OrderBy<F> orderBy(Stream<BiFunction<CriteriaBuilder, Path<?>, Order>> orders) {
        return new OrderBy<>(getFrom(), orders);
    }

}
