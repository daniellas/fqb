package com.lynx.fqb.predicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.Paths;
import com.lynx.fqb.util.Combinators;

public interface Predicates {

    public interface PredicateApplier<R> extends BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> {
    }

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate[]> of(BiFunction<CriteriaBuilder, Path<? extends R>, Predicate>... predicates) {
        return Combinators.fromBiFunctionArray(predicates, Predicate[]::new);
    }

    @SafeVarargs
    public static <R> PredicateApplier<R> and(BiFunction<CriteriaBuilder, Path<? extends R>, Predicate>... predicates) {
        return (cb, root) -> {
            return cb.and(Arrays.stream(predicates).map(p -> p.apply(cb, root)).toArray(Predicate[]::new));
        };
    }

    @SafeVarargs
    public static <R> PredicateApplier<R> or(BiFunction<CriteriaBuilder, Path<? extends R>, Predicate>... predicates) {
        return (cb, root) -> {
            return cb.or(Arrays.stream(predicates).map(p -> p.apply(cb, root)).toArray(Predicate[]::new));
        };
    }

    public static <R, V> PredicateApplier<R> equal(Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.equal(path.apply(root), value);
        };
    }

    public static <R, V> PredicateApplier<R> equal(SingularAttribute<R, V> attr, V value) {
        return equal(Paths.get(attr), value);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notEqual(Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.notEqual(path.apply(root), value);
        };
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(Function<Path<? extends R>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return path.apply(root).in(values);
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> in(Function<Path<? extends R>, ? extends Expression<V>> path, Collection<V> values) {
        return (cb, root) -> {
            return path.apply(root).in(values);
        };
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(Function<Path<? extends R>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return path.apply(root).in(values).not();
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> notIn(Function<Path<? extends R>, ? extends Expression<V>> path, Collection<V> values) {
        return (cb, root) -> {
            return path.apply(root).in(values).not();
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNotNull(Function<Path<? extends R>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return path.apply(root).isNotNull();
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNotNull(SingularAttribute<R, ?> attr) {
        return isNotNull(Paths.get(attr));
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNull(Function<Path<? extends R>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return path.apply(root).isNull();
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> isNull(SingularAttribute<R, ?> attr) {
        return isNull(Paths.get(attr));
    }

    
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> like(Function<Path<? extends R>, ? extends Expression<String>> path, String pattern) {
        return (cb, root) -> {
            return cb.like(path.apply(root), pattern);
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> contains(Function<Path<? extends R>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern + "%");
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> contains(SingularAttribute<R, String> attr, String pattern) {
        return contains(Paths.get(attr), pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> startsWith(Function<Path<? extends R>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> endsWith(Function<Path<? extends R>, ? extends Expression<String>> path, String pattern) {
        return like(path, pattern + "%");
    }

    public static <R, V extends Number> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> gt(Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.gt(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThan(Function<Path<? extends R>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return cb.greaterThan(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> greaterThanOrEqualTo(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.greaterThanOrEqualTo(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThan(Function<Path<? extends R>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return cb.lessThan(path.apply(root), value);
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Path<? extends R>, Predicate> lessThanOrEqualTo(
            Function<Path<? extends R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return cb.lessThanOrEqualTo(path.apply(root), value);
        };
    }

}
