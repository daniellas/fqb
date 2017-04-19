package com.lynx.fqb.select;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.expression.Expressions.Context;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.util.Combinators;

// FIXME Move to separate package. Currently, after moving the project fails to compile
public interface Selections {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> of(BiFunction<CriteriaBuilder, Root<R>, Selection<?>>... selections) {
        return Combinators.fromBiFunctionList(selections, Selection<?>[]::new);
    }

    public static <R, T> BiFunction<CriteriaBuilder, Root<R>, Selection<?>> path(Function<Path<R>, Path<T>> path) {
        return (cb, root) -> {
            return path.apply(root);
        };
    }

    public static <R, T> BiFunction<CriteriaBuilder, Root<R>, Selection<?>> attr(SingularAttribute<R, T> attr) {
        return path(Paths.get(attr));
    }

    public static <R, E> BiFunction<CriteriaBuilder, Root<R>, Selection<?>> expr(BiFunction<CriteriaBuilder, Root<R>, Context<R, E>> expression) {
        return (cb, root) -> {
            return expression.apply(cb, root).getExpression();
        };
    }

}
