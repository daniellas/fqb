package com.lynx.fqb.predicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.Paths;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

// TODO Add support for disjunction/conjunction
public interface Predicates {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Predicate[]> of(BiFunction<CriteriaBuilder, Root<R>, Context<R>>... predicates) {
        return (cb, root) -> {
            return Arrays.stream(predicates).map(p -> p.apply(cb, root).getPredicate()).toArray(Predicate[]::new);
        };
    }

    @SafeVarargs
    public static <R> Function<Context<R>, Context<R>> and(BiFunction<CriteriaBuilder, Root<R>, Context<R>>... predicates) {
        return ctx -> {
            return Context.of(
                    ctx.getCb(),
                    ctx.getRoot(),
                    ctx.getCb().and(Stream.concat(
                            Arrays.stream(new Predicate[] { ctx.getPredicate() }),
                            Arrays.stream(predicates).map(p -> p.apply(ctx.getCb(), ctx.getRoot()).getPredicate())).toArray(Predicate[]::new)));
        };
    }

    @SafeVarargs
    public static <R> Function<Context<R>, Context<R>> or(BiFunction<CriteriaBuilder, Root<R>, Context<R>>... predicates) {
        return ctx -> {
            ctx.getCb().conjunction();
            return Context.of(
                    ctx.getCb(),
                    ctx.getRoot(),
                    ctx.getCb().or(Stream.concat(
                            Arrays.stream(new Predicate[] { ctx.getPredicate() }),
                            Arrays.stream(predicates).map(p -> p.apply(ctx.getCb(), ctx.getRoot()).getPredicate())).toArray(Predicate[]::new)));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R>> equal(Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.equal(path.apply(root), value));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R>> equal(SingularAttribute<? super R, V> attr, V value) {
        return equal(Paths.get(attr), value);
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R>> notEqual(Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.notEqual(path.apply(root), value));
        };
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R>> in(Function<Path<R>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return Context.of(cb, root, path.apply(root).in(values));
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R>> in(Function<Path<R>, ? extends Expression<V>> path, Collection<V> values) {
        return (cb, root) -> {
            return Context.of(cb, root, path.apply(root).in(values));
        };
    }

    @SafeVarargs
    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R>> notIn(Function<Path<R>, ? extends Expression<V>> path, V... values) {
        return (cb, root) -> {
            return Context.of(cb, root, path.apply(root).in(values).not());
        };
    }

    public static <R, V> BiFunction<CriteriaBuilder, Root<R>, Context<R>> notIn(Function<Path<R>, ? extends Expression<V>> path, Collection<V> values) {
        return (cb, root) -> {
            return Context.of(cb, root, path.apply(root).in(values).not());
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R>> isNotNull(Function<Path<R>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return Context.of(cb, root, path.apply(root).isNotNull());
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R>> isNull(Function<Path<R>, ? extends Expression<?>> path) {
        return (cb, root) -> {
            return Context.of(cb, root, path.apply(root).isNull());
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R>> like(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.like(path.apply(root), pattern));
        };
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R>> contains(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern + "%");
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R>> contains(SingularAttribute<? super R, String> attr, String pattern) {
        return contains(Paths.get(attr), pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R>> startsWith(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return like(path, "%" + pattern);
    }

    public static <R> BiFunction<CriteriaBuilder, Root<R>, Context<R>> endsWith(Function<Path<R>, ? extends Expression<String>> path, String pattern) {
        return like(path, pattern + "%");
    }

    public static <R, V extends Number> BiFunction<CriteriaBuilder, Root<R>, Context<R>> gt(Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.gt(path.apply(root), value));
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Context<R>> greaterThan(Function<Path<R>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.greaterThan(path.apply(root), value));
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Context<R>> greaterThanOrEqualTo(
            Function<Path<R>, ? extends Expression<V>> path, V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.greaterThanOrEqualTo(path.apply(root), value));
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Context<R>> lessThan(Function<Path<R>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.lessThan(path.apply(root), value));
        };
    }

    public static <R, V extends Comparable<V>> BiFunction<CriteriaBuilder, Root<R>, Context<R>> lessThanOrEqualTo(
            Function<Path<R>, ? extends Expression<V>> path,
            V value) {
        return (cb, root) -> {
            return Context.of(cb, root, cb.lessThanOrEqualTo(path.apply(root), value));
        };
    }

    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class Context<R> {
        private final CriteriaBuilder cb;
        private final Root<R> root;
        private final Predicate predicate;
    }

}
