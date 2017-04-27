package com.lynx.fqb.intercept;

import java.util.Optional;
import java.util.function.Function;

@FunctionalInterface
public interface EntityInterceptor<E> extends Function<E, Optional<E>> {

    public static <E> EntityInterceptor<E> noOp() {
        return e -> Optional.of(e);
    }
}
