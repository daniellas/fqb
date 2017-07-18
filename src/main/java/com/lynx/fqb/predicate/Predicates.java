package com.lynx.fqb.predicate;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.expression.Expressions;
import com.lynx.fqb.expression.Expressions.Context;
import com.lynx.fqb.util.Combinators;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Predicates {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> of(
            BiFunction<CriteriaBuilder, Path<? extends R>, Predicate>... predicates) {
        return Combinators.fromBiFunctionArray(predicates, Predicate[]::new);
    }

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> and(
            BiFunction<CriteriaBuilder, Path<? extends R>, Predicate>... predicates) {
        return (cb, root) -> cb.and(Combinators.fromBiFunctionArray(predicates, Predicate[]::new).apply(cb, root));
    }

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> or(
            BiFunction<CriteriaBuilder, Path<? extends R>, Predicate>... predicates) {
        return (cb, root) -> cb.or(Combinators.fromBiFunctionArray(predicates, Predicate[]::new).apply(cb, root));
    }

    // equal
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> equal(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::equal, cb, root, value);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> equal(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return equal(Expressions.ofPath(path), value);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> equal(
            SingularAttribute<? super R, V> attr, V value) {
        return equal(Expressions.ofAttr(attr), value);
    }

    // notEqual
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notEqual(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::notEqual, cb, root, value);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notEqual(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return notEqual(Expressions.ofPath(path), value);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notEqual(
            SingularAttribute<? super R, V> attr, V value) {
        return notEqual(Expressions.ofAttr(attr), value);
    }

    // in
    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression,
            V... values) {
        return (cb, root) -> expression.apply(cb, root).getExpression().in(values);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression,
            Collection<V> values) {
        return (cb, root) -> expression.apply(cb, root).getExpression().in(values);
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(
            Function<Path<? extends R>, ? extends Expression<V>> path, V... values) {
        return in(Expressions.ofPath(path), values);
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(
            SingularAttribute<? super R, V> attr, V... values) {
        return in(Expressions.ofAttr(attr), values);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(
            Function<Path<? extends R>, ? extends Expression<V>> path, Collection<V> values) {
        return in(Expressions.ofPath(path), values);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(
            SingularAttribute<? super R, V> attr, Collection<V> values) {
        return in(Expressions.ofAttr(attr), values);
    }

    // notIn
    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression,
            V... values) {
        return (cb, root) -> expression.apply(cb, root).getExpression().in(values).not();
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression,
            Collection<V> values) {
        return (cb, root) -> expression.apply(cb, root).getExpression().in(values).not();
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(
            Function<Path<? extends R>, ? extends Expression<V>> path,
            V... values) {
        return notIn(Expressions.ofPath(path), values);
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(
            SingularAttribute<? super R, V> attr, V... values) {
        return notIn(Expressions.ofAttr(attr), values);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(
            Function<Path<? extends R>, ? extends Expression<V>> path,
            Collection<V> values) {
        return notIn(Expressions.ofPath(path), values);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(
            SingularAttribute<? super R, V> attr, Collection<V> values) {
        return notIn(Expressions.ofAttr(attr), values);
    }

    // notNull
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNotNull(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression) {
        return (cb, root) -> expression.apply(cb, root).getExpression().isNotNull();
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNotNull(
            Function<Path<? extends R>, ? extends Expression<V>> path) {
        return isNotNull(Expressions.ofPath(path));
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNotNull(
            SingularAttribute<? super R, V> attr) {
        return isNotNull(Expressions.ofAttr(attr));
    }

    // isNull
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNull(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression) {
        return (cb, root) -> expression.apply(cb, root).getExpression().isNull();
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNull(
            Function<Path<? extends R>, ? extends Expression<V>> path) {
        return isNull(Expressions.ofPath(path));
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNull(
            SingularAttribute<? super R, V> attr) {
        return isNull(Expressions.ofAttr(attr));
    }

    // Like
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> like(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, String>> expression, String pattern) {
        return (cb, root) -> applyExpressionContext(expression, cb::like, cb, root, pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> like(
            Function<Path<? extends R>, ? extends Expression<String>> path, String pattern) {
        return like(Expressions.ofPath(path), pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> like(
            SingularAttribute<? super R, String> attr,
            String pattern) {
        return like(Expressions.ofAttr(attr), pattern);
    }

    // Contains
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> contains(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, String>> expression,
            String pattern) {
        return like(expression, "%" + pattern + "%");
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> contains(
            Function<Path<? extends R>, ? extends Expression<String>> path,
            String pattern) {
        return contains(Expressions.ofPath(path), pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> contains(
            SingularAttribute<? super R, String> attr, String pattern) {
        return contains(Expressions.ofAttr(attr), pattern);
    }

    // Starts with
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> startsWith(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, String>> expression,
            String pattern) {
        return like(expression, pattern + "%");
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> startsWith(
            Function<Path<? extends R>, ? extends Expression<String>> path,
            String pattern) {
        return startsWith(Expressions.ofPath(path), pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> startsWith(
            SingularAttribute<? super R, String> attr, String pattern) {
        return startsWith(Expressions.ofAttr(attr), pattern);
    }

    // endsWith
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> endsWith(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, String>> expression, String pattern) {
        return like(expression, "%" + pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> endsWith(
            Function<Path<? extends R>, ? extends Expression<String>> path,
            String pattern) {
        return endsWith(Expressions.ofPath(path), pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> endsWith(
            SingularAttribute<? super R, String> attr, String pattern) {
        return endsWith(Expressions.ofAttr(attr), pattern);
    }

    // gt
    public static <R, V extends Number> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> gt(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::gt, cb, root, value);
    }

    public static <R, V extends Number> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> gt(
            SingularAttribute<? super R, V> attr, V value) {
        return gt(Expressions.ofAttr(attr), value);
    }

    public static <R, V extends Number> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> gt(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return gt(Expressions.ofPath(path), value);
    }

    // lt
    public static <R, V extends Number> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lt(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::lt, cb, root, value);
    }

    public static <R, V extends Number> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lt(
            SingularAttribute<? super R, V> attr, V value) {
        return lt(Expressions.ofAttr(attr), value);
    }

    public static <R, V extends Number> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lt(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return lt(Expressions.ofPath(path), value);
    }

    // greaterThan
    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThan(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::greaterThan, cb, root, value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThan(
            SingularAttribute<? super R, V> attr, V value) {
        return greaterThan(Expressions.ofAttr(attr), value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThan(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return greaterThan(Expressions.ofPath(path), value);
    }

    // greaterThenOrEqualTo
    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThanOrEqualTo(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::greaterThanOrEqualTo, cb, root, value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThanOrEqualTo(
            SingularAttribute<? super R, V> attr, V value) {
        return greaterThanOrEqualTo(Expressions.ofAttr(attr), value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThanOrEqualTo(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return greaterThanOrEqualTo(Expressions.ofPath(path), value);
    }

    // lessThan
    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThan(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::lessThan, cb, root, value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThan(
            SingularAttribute<? super R, V> attr, V value) {
        return lessThan(Expressions.ofAttr(attr), value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThan(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return lessThan(Expressions.ofPath(path), value);
    }

    // lessThanOrEqualTo
    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThanOrEqualTo(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expression, V value) {
        return (cb, root) -> applyExpressionContext(expression, cb::lessThanOrEqualTo, cb, root, value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThanOrEqualTo(
            SingularAttribute<? super R, V> attr, V value) {
        return lessThanOrEqualTo(Expressions.ofAttr(attr), value);
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThanOrEqualTo(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return lessThanOrEqualTo(Expressions.ofPath(path), value);
    }

    private static <R, V> Predicate applyExpressionContext(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, V>> expressionContext,
            BiFunction<Expression<V>, V, Predicate> predicate,
            CriteriaBuilder cb,
            Path<? extends R> root,
            V value) {
        return predicate.apply(expressionContext.apply(cb, root).getExpression(), value);
    }

}
