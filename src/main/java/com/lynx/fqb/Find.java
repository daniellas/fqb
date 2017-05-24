package com.lynx.fqb;

import com.lynx.fqb.intercept.EntityInterceptor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.function.Function;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Find {

    public static <E, I> IdApplier<E, I> entity(Class<E> entityCls) {
        return new InterceptingFind<E>(EntityInterceptor.noOp()).entity(entityCls);
    }

    public interface IdApplier<E, I> extends Function<I, Function<EntityManager, Optional<E>>> {
        default Function<EntityManager, Optional<E>> byId(I id) {
            return apply(id);
        }
    }

    @RequiredArgsConstructor
    public static class InterceptingFind<E> {

        private final EntityInterceptor<E> interceptor;

        public <I> IdApplier<E, I> entity(Class<E> entityCls) {
            return id -> {
                return em -> {
                    return Optional.ofNullable(em.find(entityCls, id))
                            .flatMap(e -> interceptor.apply(em, e));
                };
            };
        }

    }

}
