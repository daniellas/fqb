package com.lynx.fqb.get;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;

public interface IdApplier<E, I> extends Function<I, Function<EntityManager, Optional<E>>> {

    default Function<EntityManager, Optional<E>> byId(I id) {
        return apply(id);
    }
}
