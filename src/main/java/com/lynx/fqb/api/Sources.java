package com.lynx.fqb.api;

import com.lynx.fqb.From;

public interface Sources extends EntityManagerSupplier {

    default <F> From<F> from(Class<F> fromCls) {
        return new From<>(getEntityManager(), fromCls);
    }
}
