package com.lynx.fqb.group;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.expression.Expressions.Context;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.util.Combinators;

public interface Groupings {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Expression<?>[]> of(BiFunction<CriteriaBuilder, Root<R>, Expression<?>>... expressions) {
        return Combinators.fromBiFunctionList(expressions, Expression<?>[]::new);
    }

    public static <R, T> BiFunction<CriteriaBuilder, Root<R>, Expression<?>> fromPath(Function<Path<R>, Path<T>> path) {
        return (cb, root) -> {
            return path.apply(root);
        };
    }

    public static <R, T> BiFunction<CriteriaBuilder, Root<R>, Expression<?>> fromAttr(SingularAttribute<R, T> attr) {
        return fromPath(Paths.get(attr));
    }

    public static <R, E> BiFunction<CriteriaBuilder, Root<R>, Expression<?>> fromExpr(BiFunction<CriteriaBuilder, Root<R>, Context<R, E>> expression) {
        return (cb, root) -> {
            return expression.apply(cb, root).getExpression();
        };
    }

}
