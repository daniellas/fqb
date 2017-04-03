package com.lynx.fqb.func.clause;

import com.lynx.fqb.func.clause.impl.RootSelectionImpl;

public interface ResultSelection<S> {

    Class<S> getSelectionCls();

    default <R> RootSelection<S, R> from(Class<R> rootCls) {
        return RootSelectionImpl.of(getSelectionCls(), rootCls);
    }

}
