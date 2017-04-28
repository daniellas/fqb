package com.lynx.fqb.group;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.expression.Expressions.Context;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.util.Combinators;

public interface Groupings {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Path<R>, Expression<?>[]> of(BiFunction<CriteriaBuilder, Path<R>, Expression<?>>... expressions) {
        return Combinators.fromBiFunctionList(expressions, Expression<?>[]::new);
    }

    public static <R, T> BiFunction<CriteriaBuilder, Path<R>, Expression<?>> byPath(Function<Path<R>, Path<T>> path) {
        return (cb, root) -> {
            return path.apply(root);
        };
    }

    public static <R, T> BiFunction<CriteriaBuilder, Path<R>, Expression<?>> byAttr(SingularAttribute<R, T> attr) {
        return byPath(Paths.get(attr));
    }

    public static <R, E> BiFunction<CriteriaBuilder, Path<R>, Expression<?>> byExpr(BiFunction<CriteriaBuilder, Path<R>, Context<R, E>> expression) {
        return (cb, root) -> {
            return expression.apply(cb, root).getExpression();
        };
    }

}
