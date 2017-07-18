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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Orders {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Order[]> of(
            BiFunction<CriteriaBuilder, Path<? extends R>, Order>... orders) {
        return Combinators.fromBiFunctionArray(orders, Order[]::new);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Order> asc(
            Function<Path<? extends R>, ? extends Expression<?>> path) {
        return (cb, root) -> cb.asc(path.apply(root));
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Order> asc(SingularAttribute<? super R, ?> attr) {
        return (cb, root) -> cb.asc(root.get(attr));
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Order> asc(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, ?>> expr) {
        return (cb, root) -> cb.asc(expr.apply(cb, root).getExpression());
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Order> desc(
            Function<Path<? extends R>, ? extends Expression<?>> path) {
        return (cb, root) -> cb.desc(path.apply(root));
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Order> desc(SingularAttribute<? super R, ?> attr) {
        return desc(Paths.get(attr));
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Order> desc(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, ?>> expr) {
        return (cb, root) -> cb.desc(expr.apply(cb, root).getExpression());
    }

}
