package com.lynx.fqb.select;

import java.util.function.Supplier;

public interface Sources extends SelectSupplier {

    default <F> From<F> from(Class<F> fromCls) {
        return new From<>(getSelect(), fromCls);
    }

    default <F> From<F> from(Supplier<Class<F>> fromClsSupplier) {
        return new From<>(getSelect(), fromClsSupplier);
    }

}
