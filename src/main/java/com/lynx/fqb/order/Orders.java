package com.lynx.fqb.order;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.expression.Expressions.Context;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.util.Combinators;

public interface Orders {

    @SafeVarargs
    public static <T> BiFunction<CriteriaBuilder, Path<? extends T>, Order[]> of(BiFunction<CriteriaBuilder, Path<? extends T>, Order>... orders) {
        return Combinators.fromBiFunctionArray(orders, Order[]::new);
    }

    public static <T> BiFunction<CriteriaBuilder, Path<? extends T>, Order> asc(Function<Path<? extends T>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return cb.asc(path.apply(root));
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Path<? extends T>, Order> asc(SingularAttribute<T, ?> attr) {
        return (cb, root) -> {
            return cb.asc(root.get(attr));
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Path<? extends T>, Order> asc(BiFunction<CriteriaBuilder, Path<? extends T>, Context<T, ?>> expr) {
        return (cb, root) -> {
            return cb.asc(expr.apply(cb, root).getExpression());
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Path<? extends T>, Order> desc(Function<Path<? extends T>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return cb.desc(path.apply(root));
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Path<? extends T>, Order> desc(SingularAttribute<T, ?> attr) {
        return desc(Paths.get(attr));
    }

    public static <T> BiFunction<CriteriaBuilder, Path<? extends T>, Order> desc(BiFunction<CriteriaBuilder, Path<? extends T>, Context<T, ?>> expr) {
        return (cb, root) -> {
            return cb.desc(expr.apply(cb, root).getExpression());
        };
    }

}
