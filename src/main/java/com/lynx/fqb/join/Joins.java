package com.lynx.fqb.join;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;

public interface Joins {

    @SafeVarargs
    public static <A> BiFunction<CriteriaBuilder, From<A, A>, Join<?, ?>[]> of(BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>>... joins) {
        return (cb, from) -> {
            return Arrays.stream(joins).map(j -> j.apply(cb, from)).toArray(Join[]::new);
        };
    }

    public interface JoinApplier<A> extends BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>> {
    }

    public static <A> JoinApplier<A> join(SingularAttribute<? super A, ?> attr, JoinType type,
            Optional<BiFunction<CriteriaBuilder, Path<A>, Predicate[]>> predicates) {
        return (cb, from) -> {
            if (predicates.isPresent()) {
                return from.join(attr, type).on(predicates.get().apply(cb, from));
            }

            return from.join(attr, type);
        };
    }

    public static <A, B> JoinApplier<A> join(ListAttribute<? super A, ?> attr, JoinType type,
            Optional<BiFunction<CriteriaBuilder, Path<B>, Predicate[]>> predicates) {
        return (cb, from) -> {
            return from.join(attr, type);
        };
    }

    public static <A> JoinApplier<A> inner(SingularAttribute<? super A, ?> attr, BiFunction<CriteriaBuilder, Path<A>, Predicate[]> predicates) {
        return join(attr, JoinType.INNER, Optional.of(predicates));
    }

    public static <A> JoinApplier<A> inner(SingularAttribute<? super A, ?> attr) {
        return join(attr, JoinType.INNER, Optional.empty());
    }

}
