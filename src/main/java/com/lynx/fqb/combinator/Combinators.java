package com.lynx.fqb.combinator;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Combinators {

    /**
     * Converts array of {@link BiFunction}'s to single {@link BiFunction}
     * returning array of result type
     * 
     * @param funcs
     *            to apply
     * @param constructor
     *            of {@link Stream} toAray() method
     * @return {@link BiFunction}
     */
    public static <B, R, I> BiFunction<B, R, I[]> fromBiFunctionList(BiFunction<B, R, ? extends I>[] funcs, IntFunction<I[]> constructor) {
        return (cb, root) -> {
            return Arrays.stream(funcs).map(i -> i.apply(cb, root)).toArray(constructor);
        };
    }

    public static <R, I> BiFunction<CriteriaBuilder, Root<R>, I[]> fromFunctionList(Function<Path<R>, ? extends I>[] funcs, IntFunction<I[]> constructor) {
        return (cb, root) -> {
            return Arrays.stream(funcs).map(i -> i.apply(root)).toArray(constructor);
        };
    }

}
