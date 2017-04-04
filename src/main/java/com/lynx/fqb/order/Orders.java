package com.lynx.fqb.order;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.lynx.fqb.combinator.Combinators;

public interface Orders {

    @SafeVarargs
    public static <T> BiFunction<CriteriaBuilder, Root<T>, Order[]> of(BiFunction<CriteriaBuilder, Root<T>, Order>... orders) {
        return Combinators.fromBiFunctionList(orders, Order[]::new);
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Order> asc(Function<Path<T>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return cb.asc(path.apply(root));
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Order> desc(Function<Path<T>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return cb.desc(path.apply(root));
        };
    }

}
