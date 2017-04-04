package com.lynx.fqb;

import java.util.function.Function;

import javax.persistence.EntityManager;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Persist {

    public static <E> Function<EntityManager, E> entity(E entity) {
        return em -> {
            em.persist(entity);

            return entity;
        };
    }
}
