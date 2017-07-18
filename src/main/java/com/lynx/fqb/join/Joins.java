package com.lynx.fqb.join;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.FetchParent;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.util.Combinators;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Joins {

    @SafeVarargs
    public static <A> BiFunction<CriteriaBuilder, From<A, A>, FetchParent<?, ?>[]> of(
            BiFunction<CriteriaBuilder, From<A, A>, ? extends FetchParent<?, ?>>... joins) {
        return Combinators.fromBiFunctionArray(joins, FetchParent<?, ?>[]::new);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> join(SingularAttribute<? super A, B> attr,
            JoinType type,
            Optional<BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]>> predicates) {
        return (cb, from) -> {
            Join<A, B> join = from.join(attr, type);

            return predicates
                    .map(p -> join.on(predicates.get().apply(cb, join)))
                    .orElse(join);
        };
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> join(ListAttribute<? super A, B> attr,
            JoinType type) {
        return (cb, from) -> from.join(attr, type);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> inner(ListAttribute<? super A, B> attr) {
        return join(attr, JoinType.INNER);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> left(ListAttribute<? super A, B> attr) {
        return join(attr, JoinType.LEFT);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> inner(SingularAttribute<? super A, B> attr,
            BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]> predicates) {
        return join(attr, JoinType.INNER, Optional.of(predicates));
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> inner(
            SingularAttribute<? super A, B> attr) {
        return join(attr, JoinType.INNER, Optional.empty());
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> left(SingularAttribute<? super A, B> attr,
            BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]> predicates) {
        return join(attr, JoinType.LEFT, Optional.of(predicates));
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, From<A, B>> left(
            SingularAttribute<? super A, B> attr) {
        return join(attr, JoinType.LEFT, Optional.empty());
    }

    public static <A, B, C> Function<From<A, B>, From<B, C>> cascade(SingularAttribute<? super B, C> attr,
            JoinType type) {
        return join -> join.join(attr, type);
    }

    public static <A, B, C> Function<From<A, B>, From<B, C>> cascadeInner(SingularAttribute<? super B, C> attr) {
        return cascade(attr, JoinType.INNER);
    }

    public static <A, B, C> Function<From<A, B>, From<B, C>> cascadeLeft(SingularAttribute<? super B, C> attr) {
        return cascade(attr, JoinType.LEFT);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, FetchParent<A, B>> fetch(
            SingularAttribute<? super A, B> attr,
            JoinType type) {
        return (cb, from) -> from.fetch(attr, type);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, FetchParent<A, B>> fetchInner(
            SingularAttribute<? super A, B> attr) {
        return fetch(attr, JoinType.INNER);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, FetchParent<A, B>> fetchLeft(
            SingularAttribute<? super A, B> attr) {
        return fetch(attr, JoinType.LEFT);
    }

    public static <A, B, C> Function<FetchParent<A, B>, FetchParent<B, C>> cascadeFetch(
            SingularAttribute<? super B, C> attr,
            JoinType type) {
        return join -> join.fetch(attr, type);
    }

    public static <A, B, C> Function<FetchParent<A, B>, FetchParent<B, C>> cascadeFetchInner(
            SingularAttribute<? super B, C> attr) {
        return cascadeFetch(attr, JoinType.INNER);
    }

    public static <A, B, C> Function<FetchParent<A, B>, FetchParent<B, C>> cascadeFetchLeft(
            SingularAttribute<? super B, C> attr) {
        return cascadeFetch(attr, JoinType.LEFT);
    }

}
