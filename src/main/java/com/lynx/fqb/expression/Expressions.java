package com.lynx.fqb.expression;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.lynx.fqb.combinator.Combinators;

public interface Expressions {

    @SafeVarargs
    public static <T> BiFunction<CriteriaBuilder, Root<T>, Expression<?>[]> of(BiFunction<CriteriaBuilder, Root<T>, Expression<?>>... expressions) {
        return Combinators.fromBiFunctionList(expressions, Expression<?>[]::new);
    }

    public static <T> BiFunction<CriteriaBuilder, Root<T>, Expression<?>> count(Class<T> rootCls) {
        return (cb, root) -> {
            return cb.count(root);
        };
    }

}
