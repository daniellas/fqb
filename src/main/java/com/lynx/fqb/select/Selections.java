package com.lynx.fqb.select;

import java.util.function.Supplier;

import com.lynx.fqb.path.MultiplePathApplier;

public interface Selections<R> extends QueryContextSupplier {

    default ResultOperations as(Supplier<Class<R>> resultCls, Supplier<MultiplePathApplier> paths) {
        return new Result<>(getQueryContext(), resultCls, paths);
    }

    default ResultOperations as(Class<R> resultCls, MultiplePathApplier paths) {
        return as(() -> resultCls, () -> paths);
    }

}
