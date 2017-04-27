package com.lynx.fqb;

import java.util.function.Function;

import javax.persistence.EntityManager;

import com.lynx.fqb.intercept.EntityInterceptor;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Remove {

    public static <E> Function<EntityManager, Boolean> entity(E entity) {
        return new InterceptingRemove<>(EntityInterceptor.noOp()).entity(entity);
    }

    @RequiredArgsConstructor
    public static class InterceptingRemove<E> {

        private final EntityInterceptor<E> interceptor;

        public Function<EntityManager, Boolean> entity(E entity) {
            return em -> {
                return interceptor.apply(em, entity).map(e -> {
                    em.remove(e);

                    return true;
                }).orElse(false);
            };
        }
    }

}
