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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Groupings {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>[]> of(
            BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>>... expressions) {
        return Combinators.fromBiFunctionArray(expressions, Expression<?>[]::new);
    }

    public static <R, T> BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>> byPath(
            Function<Path<? extends R>, Path<T>> path) {
        return (cb, root) -> path.apply(root);
    }

    public static <R, T> BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>> byAttr(
            SingularAttribute<? super R, T> attr) {
        return byPath(Paths.get(attr));
    }

    public static <R, E> BiFunction<CriteriaBuilder, Path<? extends R>, Expression<?>> byExpr(
            BiFunction<CriteriaBuilder, Path<? extends R>, Context<R, E>> expression) {
        return (cb, root) -> expression.apply(cb, root).getExpression();
    }

}
