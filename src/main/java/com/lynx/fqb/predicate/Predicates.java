package com.lynx.fqb.predicate;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Predicates {

    @SafeVarargs
    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate[]> of(BiFunction<CriteriaBuilder, Root<T>, Predicate>... predicates) {
        return (cb, root) -> {
            return Arrays.stream(predicates).map(p -> p.apply(cb, root)).toArray(Predicate[]::new);
        };
    }

    public static <T, V> BiFunction<CriteriaBuilder, Root<T>, Predicate> equal(Function<Path<T>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.equal(path.apply(root), value);
        };
    }

    public static <T, V> BiFunction<CriteriaBuilder, Root<T>, Predicate> notEqual(Function<Path<T>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.notEqual(path.apply(root), value);
        };
    }

    @SafeVarargs
    public static <T, V> BiFunction<CriteriaBuilder, Root<T>, Predicate> in(Function<Path<T>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return path.apply(root).in(values);
        };
    }

    @SafeVarargs
    public static <T, V> BiFunction<CriteriaBuilder, Root<T>, Predicate> notIn(Function<Path<T>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return path.apply(root).in(values).not();
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate> isNotNull(Function<Path<T>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return path.apply(root).isNotNull();
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate> isNull(Function<Path<T>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return path.apply(root).isNull();
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate> like(Function<Path<T>, ? extends Expression<String>> path, String pattern) {
        return (cb, root) -> {
            return cb.like(path.apply(root), pattern);
        };
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate> contains(Function<Path<T>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern + "%");
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate> startsWith(Function<Path<T>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern);
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate> endsWith(Function<Path<T>, ? extends Expression<String>> path, String pattern) {
        return like(path, pattern + "%");
    }

    public static <T, V extends Number> BiFunction<CriteriaBuilder, Root<T>, Predicate> gt(Function<Path<T>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.gt(path.apply(root), value);
        };
    }

    public static <T, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<T>, Predicate> greaterThan(Function<Path<T>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return cb.greaterThan(path.apply(root), value);
        };
    }

}
