package com.lynx.fqb.select;

import java.util.function.Supplier;

public interface Sources extends QueryContextSupplier {

    default <F> FromOperations<F> from(Class<F> fromCls) {
        return new From<>(getQueryContext(), fromCls);
    }

    default <F> FromOperations<F> from(Supplier<Class<F>> fromClsSupplier) {
        return new From<>(getQueryContext(), fromClsSupplier);
    }

}
