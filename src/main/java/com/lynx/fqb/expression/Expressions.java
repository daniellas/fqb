package com.lynx.fqb.expression;

import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.Paths;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Expressions {

    /**
     * Create literal expression from given value
     * 
     * @param value
     *            to create expression from
     * @return expression context {@link BiFunction}
     */
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> ofValue(V value) {
        return (cb, root) -> Context.of(cb, root, cb.literal(value));
    }

    /**
     * Create literal expression from given path
     * 
     * @param path
     *            to create expression from
     * @return expression context {@link BiFunction}
     */
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> ofPath(
            Function<Path<? extends R>, ? extends Expression<V>> path) {
        return (cb, root) -> Context.of(cb, root, path.apply(root));
    }

    /**
     * Create literal expression from given attribute
     * 
     * @param attr
     *            to create expression from
     * @return expression context {@link BiFunction}
     */
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> ofAttr(
            SingularAttribute<? super R, V> attr) {
        return ofPath(Paths.get(attr));
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> count(
            Function<Path<? extends R>, ? extends Expression<V>> path) {
        return (cb, root) -> Context.of(cb, root, cb.count(path.apply(root)));
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> count(
            SingularAttribute<? super R, V> attr) {
        return count(Paths.get(attr));
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> count(Class<R> rootCls) {
        return (cb, root) -> Context.of(cb, root, cb.count(root));
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> countDistinct(
            Function<Path<? extends R>, ? extends Expression<V>> path) {
        return (cb, root) -> Context.of(cb, root, cb.countDistinct(path.apply(root)));
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> countDistinct(
            SingularAttribute<? super R, V> attr) {
        return countDistinct(Paths.get(attr));
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, Long>> countDistinct(Class<R> rootCls) {
        return (cb, root) -> Context.of(cb, root, cb.countDistinct(root));
    }

    public static <R> Function<Context<R, Long>, Context<R, Long>> count() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().count(ctx.getExpression()));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> max() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().max(ctx.getExpression()));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> min() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().min(ctx.getExpression()));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, Double>> avg() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().avg(ctx.getExpression()));
    }

    // sum
    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> sum() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().sum(ctx.getExpression()));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> sum(V value) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().sum(ctx.getExpression(), value));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> sum(
            Function<Path<? extends R>, ? extends Expression<V>> path) {
        return ctx -> Context.of(
                ctx.getCb(),
                ctx.getRoot(),
                ctx.getCb().sum(ctx.getExpression(), path.apply(ctx.getRoot())));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> sum(SingularAttribute<? super R, V> attr) {
        return sum(Paths.get(attr));
    }

    // diff
    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> diff(V value) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().diff(ctx.getExpression(), value));
    }

    // prod
    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> prod(V value) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().prod(ctx.getExpression(), value));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> prod(
            Function<Path<? extends R>, ? extends Expression<V>> path) {
        return ctx -> Context.of(
                ctx.getCb(),
                ctx.getRoot(),
                ctx.getCb().prod(ctx.getExpression(), path.apply(ctx.getRoot())));
    }

    public static <R, V extends Number> Function<Context<R, V>, Context<R, V>> prod(SingularAttribute<? super R, V> attr) {
        return prod(Paths.get(attr));
    }

    public static <R> Function<Context<R, String>, Context<R, Integer>> length() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().length(ctx.getExpression()));
    }

    public static <R> Function<Context<R, String>, Context<R, String>> upper() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().upper(ctx.getExpression()));
    }

    public static <R> Function<Context<R, String>, Context<R, String>> lower() {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().lower(ctx.getExpression()));
    }

    public static <R> Function<Context<R, String>, Context<R, String>> concat(String value) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().concat(ctx.getExpression(), value));
    }

    public static <R> Function<Context<R, String>, Context<R, String>> substring(int from) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().substring(ctx.getExpression(), from));
    }

    public static <R> Function<Context<R, String>, Context<R, String>> substring(int from, int len) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().substring(ctx.getExpression(), from, len));
    }

    public static <R, V> Function<Context<R, ?>, Context<R, V>> as(Class<V> cls) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getExpression().as(cls));
    }

    public static <R, I, O> Function<Context<R, I>, Context<R, O>> function(String name, Class<O> type) {
        return ctx -> Context.of(ctx.getCb(), ctx.getRoot(), ctx.getCb().function(name, type, ctx.getExpression()));
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
