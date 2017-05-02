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
import javax.persistence.metamodel.SingularAttribute;

public interface Joins {

    @SuppressWarnings("unchecked")
    @SafeVarargs
    public static <A> BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>[]> of(BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>>... joins) {
        return (cb, from) -> {
            return Arrays.stream(joins).map(j -> j.apply(cb, from)).toArray(Join[]::new);
        };
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>> join(SingularAttribute<? super A, B> attr, JoinType type,
            Optional<BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]>> predicates) {
        return (cb, from) -> {
            Join<A, B> join = from.join(attr, type);

            if (predicates.isPresent()) {
                return join.on(predicates.get().apply(cb, join));
            }

            return join;
        };
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>> inner(SingularAttribute<? super A, B> attr,
            BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]> predicates) {
        return join(attr, JoinType.INNER, Optional.of(predicates));
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>> inner(SingularAttribute<? super A, B> attr) {
        return join(attr, JoinType.INNER, Optional.empty());
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>> left(SingularAttribute<? super A, B> attr,
            BiFunction<CriteriaBuilder, Path<? extends B>, Predicate[]> predicates) {
        return join(attr, JoinType.LEFT, Optional.of(predicates));
    }

    public static <A, B> BiFunction<CriteriaBuilder, From<A, A>, Join<A, ?>> left(SingularAttribute<? super A, B> attr) {
        return join(attr, JoinType.LEFT, Optional.empty());
    }

}
