package com.lynx.fqb.join;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.util.Combinators;

public interface Joins {

    @SafeVarargs
    public static <A> BiFunction<CriteriaBuilder, From<A, A>, Join<?, ?>[]> of(BiFunction<CriteriaBuilder, From<A, A>, ? extends Join<?, ?>>... joins) {
        return Combinators.fromBiFunctionArray(joins, Join<?, ?>[]::new);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> join(SingularAttribute<A, B> attr, JoinType type,
            Optional<BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]>> predicates) {
        return (cb, from) -> {
            Join<A, B> join = from.join(attr, type);

            if (predicates.isPresent()) {
                return join.on(predicates.get().apply(cb, join));
            }

            return join;
        };
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> join(ListAttribute<A, B> attr, JoinType type) {
        return (cb, from) -> from.join(attr, type);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> inner(ListAttribute<A, B> attr) {
        return join(attr, JoinType.INNER);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> left(ListAttribute<A, B> attr) {
        return join(attr, JoinType.LEFT);
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> inner(SingularAttribute<A, B> attr,
            BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]> predicates) {
        return join(attr, JoinType.INNER, Optional.of(predicates));
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> inner(SingularAttribute<A, B> attr) {
        return join(attr, JoinType.INNER, Optional.empty());
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> left(SingularAttribute<A, B> attr,
            BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]> predicates) {
        return join(attr, JoinType.LEFT, Optional.of(predicates));
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, B>> left(SingularAttribute<A, B> attr) {
        return join(attr, JoinType.LEFT, Optional.empty());
    }

    public static <A, B, C> Function<Join<A, B>, Join<B, C>> cascade(SingularAttribute<B, C> attr, JoinType type) {
        return from -> from.join(attr, type);
    }

    public static <A, B, C> Function<Join<A, B>, Join<B, C>> cascadeInner(SingularAttribute<B, C> attr) {
        return cascade(attr, JoinType.INNER);
    }

    public static <A, B, C> Function<Join<A, B>, Join<B, C>> cascadeLeft(SingularAttribute<B, C> attr) {
        return cascade(attr, JoinType.LEFT);
    }

}
