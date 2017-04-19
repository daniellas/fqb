package com.lynx.fqb.join;

import java.util.Arrays;
import java.util.function.Function;

import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.SingularAttribute;

public interface Joins {

    @SafeVarargs
    public static <A> Function<From<A, A>, Join<?, ?>[]> of(Function<From<A, A>, Join<A, ?>>... joins) {
        return from -> {
            return Arrays.stream(joins).map(j -> j.apply(from)).toArray(Join[]::new);
        };
    }

    public static <A> Function<From<A, A>, Join<A, ?>> join(SingularAttribute<? super A, ?> attr, JoinType type) {
        return from -> {
            return from.join(attr, type);
        };
    }

    public static <A> Function<From<A, A>, Join<A, ?>> inner(SingularAttribute<? super A, ?> attr) {
        return join(attr, JoinType.INNER);
    }

    public static <A> Function<From<A, A>, Join<A, ?>> left(SingularAttribute<? super A, ?> attr) {
        return join(attr, JoinType.LEFT);
    }

}
