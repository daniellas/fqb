package com.lynx.fqb;

import com.lynx.fqb.intercept.EntityInterceptor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.function.Function;

/**
 * Wraps {@link EntityManager} find methods.
 * 
 * You can use it by chaining functions:
 * <p>
 * {@code
 * Find.entity(Entity.class).byId(1l).apply(entityManager);
 * }
 * 
 * @author dalas0
 *
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Find {

    /**
     * Find entity of given entityCls
     * 
     * @param entityCls
     *            class of entity to find
     * @return {@link IdApplier} to provide entity identifier
     * @param <E>
     *            entity type
     * @param <I>
     *            entity identifier type
     */
    public static <E, I> IdApplier<E, I> entity(Class<E> entityCls) {
        return new InterceptingFind<E>(EntityInterceptor.noOp()).entity(entityCls);
    }

    /**
     * Entity identifier consuming function
     * 
     * @author dalas0
     *
     * @param <E>
     *            entity type
     * @param <I>
     *            entity identifier type
     */
    public interface IdApplier<E, I> extends Function<I, Function<EntityManager, Optional<E>>> {
        /**
         * Entity id applying {@link Function}
         * 
         * @param id
         *            of entity to find
         * @return searched entity {@link Optional}
         */
        default Function<EntityManager, Optional<E>> byId(I id) {
            return apply(id);
        }
    }

    /**
     * Intercepting implementation of {@link EntityManager} find methods
     * 
     * @author dalas0
     *
     * @param <E>
     *            type of entity
     */
    @RequiredArgsConstructor
    public static class InterceptingFind<E> {

        private final EntityInterceptor<E> interceptor;

        /**
         * Find entity of given class
         * 
         * @param entityCls
         *            to find
         * @return entity identifier consuming function
         */
        public <I> IdApplier<E, I> entity(Class<E> entityCls) {
            return id -> {
                return em -> Optional.ofNullable(em.find(entityCls, id))
                        .flatMap(e -> interceptor.apply(em, e));
            };
        }

    }

}
