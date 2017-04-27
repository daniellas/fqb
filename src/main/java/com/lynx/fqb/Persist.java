package com.lynx.fqb;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;

import com.lynx.fqb.intercept.EntityInterceptor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Persist {

    public static <E> Function<EntityManager, Optional<E>> entity(E entity) {
        return new InterceptingPersist<E>(EntityInterceptor.noOp()).entity(entity);
    }

    @RequiredArgsConstructor
    public static class InterceptingPersist<E> {
        private final EntityInterceptor<E> interceptor;

        public Function<EntityManager, Optional<E>> entity(E entity) {
            return em -> {
                return interceptor.apply(em,entity).map(e -> {
                    em.persist(e);

                    return e;
                });
            };
        }

    }

}
