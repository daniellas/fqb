package com.lynx.fqb;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;

import com.lynx.fqb.intercept.EntityInterceptor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Merge {

    public static <E> Function<EntityManager, Optional<E>> entity(E entity) {
        return new InterceptingMerge<E>(EntityInterceptor.noOp()).entity(entity);
    }

    @RequiredArgsConstructor
    public static class InterceptingMerge<E> {
        private final EntityInterceptor<E> interceptor;

        public Function<EntityManager, Optional<E>> entity(E entity) {
            return em -> {
                return interceptor.apply(em,entity).map(em::merge);
            };
        }

    }
}
