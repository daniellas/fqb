package com.lynx.fqb.util;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.IntFunction;
import java.util.stream.Stream;

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
     * @param generator
     *            of {@link Stream} toAray() method
     * @return {@link BiFunction}
     */
    public static <B, R, I> BiFunction<B, R, I[]> fromBiFunctionArray(BiFunction<B, R, ? extends I>[] funcs, IntFunction<I[]> generator) {
        return (cb, root) -> Arrays.stream(funcs).map(i -> i.apply(cb, root)).toArray(generator);
    }

}
