package com.lynx.fqb.join;

import java.util.Arrays;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
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

    @SafeVarargs
    public static <A> JoinApplier<A> join(SingularAttribute<? super A, ?> attr, JoinType type,
            BiFunction<CriteriaBuilder, Path<?>, Predicate>... predicates) {
        return (cb, from) -> {
            Join<A, ?> join = from.join(attr, type);

            if (predicates.length != 0) {
                Arrays.stream(predicates).map(p -> p.apply(cb, join.getParent())).toArray(Predicate[]::new);
            }

            return join;
        };
    }

    // @SafeVarargs
    // public static <A,B> JoinApplier<A,B> inner(SingularAttribute<? super A,
    // B> attr, BiFunction<CriteriaBuilder, From<A, B>, Predicate>...
    // predicates) {
    // return join(attr, JoinType.INNER, predicates);
    // }
    //
    // @SafeVarargs
    // public static <A> JoinApplier<A> left(SingularAttribute<? super A, ?>
    // attr, BiFunction<CriteriaBuilder, From<?, A>, Predicate>... predicates) {
    // return join(attr, JoinType.LEFT, predicates);
    // }

}
