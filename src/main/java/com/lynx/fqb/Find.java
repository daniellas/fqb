package com.lynx.fqb;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Find {

    public static <E, I> IdApplier<E, I> entity(Class<E> entityCls) {
        return id -> {
            return em -> {
                return Optional.ofNullable(em.find(entityCls, id));
            };
        };
    }

    public interface IdApplier<E, I> extends Function<I, Function<EntityManager, Optional<E>>> {
        default Function<EntityManager, Optional<E>> byId(I id) {
            return apply(id);
        }
    }

}
