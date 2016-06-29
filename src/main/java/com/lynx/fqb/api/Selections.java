package com.lynx.fqb.api;

import com.lynx.fqb.select.From;

import java.util.function.Supplier;

public interface Selections<R, F> extends CriteriaBuilderHolder {

    default From<R, F> select(Class<R> resultCls) {
        return new From<>(getCriteriaBuilder(), getCriteriaBuilder().createQuery(resultCls));
    }

    default From<R, F> select(Supplier<Class<R>> resultClsSupplier) {
        return new From<>(getCriteriaBuilder(), getCriteriaBuilder().createQuery(resultClsSupplier.get()));
    }

}
