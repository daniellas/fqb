package com.lynx.fqb;

import java.util.Optional;

import com.lynx.fqb.get.IdApplier;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Get {

    public static <E, I> IdApplier<E, I> from(Class<E> entityCls) {
        return id -> {
            return em -> {
                return Optional.ofNullable(em.find(entityCls, id));
            };
        };
    }
}
