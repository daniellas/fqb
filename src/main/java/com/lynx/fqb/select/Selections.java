package com.lynx.fqb.select;

import java.util.function.Supplier;

import com.lynx.fqb.path.MultiplePathProvider;
import com.lynx.fqb.select.ctx.QueryContextSupplier;

public interface Selections<R> extends QueryContextSupplier {

    default ResultOperations as(Supplier<Class<R>> resultCls, Supplier<MultiplePathProvider> paths) {
        return new Result<>(getQueryContext(), resultCls, paths);
    }

    default ResultOperations as(Class<R> resultCls, MultiplePathProvider paths) {
        return as(() -> resultCls, () -> paths);
    }

}
