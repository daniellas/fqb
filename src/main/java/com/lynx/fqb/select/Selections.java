package com.lynx.fqb.select;

import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.combinator.Combinators;

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

}
