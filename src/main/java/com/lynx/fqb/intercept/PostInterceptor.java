package com.lynx.fqb.intercept;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface PostInterceptor<E> extends Function<E, Optional<E>> {

    public static <E> PostInterceptor<E> noOp() {
        return e -> Optional.of(e);
    }
}
