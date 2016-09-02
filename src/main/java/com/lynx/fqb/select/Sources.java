package com.lynx.fqb.select;

import java.util.function.Supplier;

import com.lynx.fqb.select.ctx.QueryContextSupplier;

public interface Sources extends QueryContextSupplier {

    default <F> FromOperations<F> from(Supplier<Class<F>> fromCls) {
        return new From<>(getQueryContext(), fromCls);
    }

    default <F> FromOperations<F> from(Class<F> fromCls) {
        return from(() -> fromCls);
    }


}
