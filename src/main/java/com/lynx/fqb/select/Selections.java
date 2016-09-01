package com.lynx.fqb.select;

public interface Selections<R> extends QueryContextSupplier {

    default ResultOperations as(Class<R> resultCls) {
        return new Result<>(getQueryContext(), resultCls);
    }

}
