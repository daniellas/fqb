package com.lynx.fqb.predicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.Paths;
import com.lynx.fqb.util.Combinators;

public interface Predicates {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate[]> of(BiFunction<CriteriaBuilder, Root<R>, Predicate>... predicates) {
        return Combinators.fromBiFunctionList(predicates, Predicate[]::new);
    }

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> and(BiFunction<CriteriaBuilder, Root<R>, Predicate>... predicates) {
        return (cb, root) -> {
            return cb.and(Arrays.stream(predicates).map(p -> p.apply(cb, root)).toArray(Predicate[]::new));
        };
    }

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> or(BiFunction<CriteriaBuilder, Root<R>, Predicate>... predicates) {
        return (cb, root) -> {
            return cb.or(Arrays.stream(predicates).map(p -> p.apply(cb, root)).toArray(Predicate[]::new));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Predicate> equal(Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.equal(path.apply(root), value);
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Predicate> equal(SingularAttribute<? super R, V> attr, V value) {
        return equal(Paths.get(attr), value);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Predicate> notEqual(Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.notEqual(path.apply(root), value);
        };
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Predicate> in(Function<Path<R>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return path.apply(root).in(values);
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Predicate> in(Function<Path<R>, ? extends Expression<V>> path, Collection<V> values) {
        return (cb, root) -> {
            return path.apply(root).in(values);
        };
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Predicate> notIn(Function<Path<R>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return path.apply(root).in(values).not();
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Predicate> notIn(Function<Path<R>, ? extends Expression<V>> path, Collection<V> values) {
        return (cb, root) -> {
            return path.apply(root).in(values).not();
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> isNotNull(Function<Path<R>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return path.apply(root).isNotNull();
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> isNull(Function<Path<R>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return path.apply(root).isNull();
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> like(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return (cb, root) -> {
            return cb.like(path.apply(root), pattern);
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> contains(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern + "%");
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> contains(SingularAttribute<? super R, String> attr, String pattern) {
        return contains(Paths.get(attr), pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> startsWith(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate> endsWith(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return like(path, pattern + "%");
    }

    public static <R, V extends Number> BiFunction<CriteriaBuilder, Root<R>, Predicate> gt(Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.gt(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Predicate> greaterThan(Function<Path<R>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return cb.greaterThan(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Predicate> greaterThanOrEqualTo(
            Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.greaterThanOrEqualTo(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Predicate> lessThan(Function<Path<R>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return cb.lessThan(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Predicate> lessThanOrEqualTo(
            Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.lessThanOrEqualTo(path.apply(root), value);
        };
    }

}
