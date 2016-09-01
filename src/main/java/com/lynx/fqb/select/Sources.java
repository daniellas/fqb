package com.lynx.fqb.select;

import java.util.function.Supplier;

public interface Sources extends QueryContextSupplier {

    default <R, F> FromOperations<R, F> from(Supplier<Class<F>> fromClsSupplier) {
        return new From<>(getQueryContext(), fromClsSupplier);
    }

    default <R, F> FromOperations<R, F> from(Class<F> fromCls) {
        return from(() -> fromCls);
    }

}
