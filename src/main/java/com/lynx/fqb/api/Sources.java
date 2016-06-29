package com.lynx.fqb.api;

import com.lynx.fqb.select.Source;

import java.util.function.Supplier;

public interface Sources<R, F> extends CriteriaBuilderHolder, CriteriaQueryHolder<R> {

    default Source<R, F> from(Class<F> entityClass) {
        return new Source<>(getCriteriaBuilder(), getCriteriaQuery(), getCriteriaQuery().from(entityClass));
    }

    default Source<R, F> from(Supplier<Class<F>> entityClassSupplier) {
        return from(entityClassSupplier.get());
    }

}
