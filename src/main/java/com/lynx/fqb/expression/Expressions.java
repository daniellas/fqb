package com.lynx.fqb.expression;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.Paths;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface Expressions {

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> ofValue(V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.literal(value));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> ofPath(Function<Path<? extends R>, Path<V>> path) {
        return (cb, root) -> {
            return Context.of(cb, root, path.apply(root));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> ofAttr(SingularAttribute<? super R, V> attr) {
        return ofPath(Paths.get(attr));
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> count(Function<Path<? extends R>, Path<V>> path) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.count(path.apply(root)));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> countDistinct(Function<Path<? extends R>, Path<V>> path) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.countDistinct(path.apply(root)));
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> count(Class<R> rootCls) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.count(root));
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> countDistinct(Class<R> rootCls) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.countDistinct(root));
        };
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> max() {
        return ctx -> {
            return Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().max(ctx.getExpression()));
        };
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> sum() {
        return ctx -> {
            return Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().sum(ctx.getExpression()));
        };
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> sum(V value) {
        return ctx -> {
            return Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().sum(ctx.getExpression(), value));
        };
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> diff(V value) {
        return ctx -> {
            return Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().diff(ctx.getExpression(), value));
        };
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> prod(V value) {
        return ctx -> {
            return Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().prod(ctx.getExpression(), value));
        };
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> prod(SingularAttribute<R, V> attr) {
        return ctx -> {
            return Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().prod(ctx.getExpression(), Paths.get(attr).apply(ctx.getRoot())));
        };
    }

    public static <R, I, O> Function<Context<R, I>, Context<R, O>> function(String name, Class<O> type) {
        return ctx -> {
            return Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().function(name, type, ctx.getExpression()));
        };
    }

    public static <R> Function<Context<R, Date>, Context<R, Number>> year() {
        return function("year", Number.class);
    }

    public static <R> Function<Context<R, Date>, Context<R, Number>> month() {
        return function("month", Number.class);
    }

    public static <R> Function<Context<R, Date>, Context<R, Number>> dayOfMonth() {
        return function("dayofmonth", Number.class);
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class Context<R, E> {
        private final CriteriaBuilder cb;
        private final Path<? extends R> root;
        private final Expression<E> expression;
    }
}
