package com.lynx.fqb.select;

import java.util.function.Supplier;

public interface Sources extends Supplier<Select> {

    default <F> From<F> from(Class<F> fromCls) {
        return new From<>(get(), fromCls);
    }

    default <F> From<F> from(Supplier<Class<F>> fromClsSupplier) {
        return new From<>(get(), fromClsSupplier);
    }

}
