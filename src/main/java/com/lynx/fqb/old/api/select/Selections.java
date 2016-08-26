package com.lynx.fqb.old.api.select;

import com.lynx.fqb.old.api.CriteriaBuilderHolder;
import com.lynx.fqb.old.select.structure.From;

import java.util.function.Supplier;

public interface Selections<R, F> extends CriteriaBuilderHolder {

    default From<R, F> select(Class<R> resultCls) {
        return new From<>(getCriteriaBuilder(), getCriteriaBuilder().createQuery(resultCls));
    }

    default From<R, F> select(Supplier<Class<R>> resultClsSupplier) {
        return new From<>(getCriteriaBuilder(), getCriteriaBuilder().createQuery(resultClsSupplier.get()));
    }

}
