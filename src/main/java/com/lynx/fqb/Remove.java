package com.lynx.fqb;

import java.util.function.Consumer;

import javax.persistence.EntityManager;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Remove {

    public static <E, I> Consumer<EntityManager> entity(E entity) {
        return em -> {
            em.remove(entity);
        };
    }

}
