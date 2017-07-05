package com.lynx.fqb.intercept;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EntityInterceptor<E> extends BiFunction<EntityManager, E, Optional<E>> {

    public static <E> EntityInterceptor<E> noOp() {
        return (em, e) -> Optional.of(e);
    }
}
