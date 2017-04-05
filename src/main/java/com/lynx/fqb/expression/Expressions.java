package com.lynx.fqb.expression;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface Expressions {

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R, Long>> count(Function<Path<R>, Path<V>> path) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.count(path.apply(root)));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R, Long>> countDistinct(Function<Path<R>, Path<V>> path) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.countDistinct(path.apply(root)));
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R, Long>> count(Class<R> rootCls) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.count(root));
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R, Long>> countDistinct(Class<R> rootCls) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.countDistinct(root));
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

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class Context<R, E> {
        private final CriteriaBuilder cb;
        private final Root<R> root;
        private final Expression<E> expression;
    }
}
