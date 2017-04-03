package com.lynx.fqb.select;

import com.lynx.fqb.select.impl.RootSelectionImpl;

public interface ResultSelection<S> {

    Class<S> getSelectionCls();

    default <R> RootSelection<S, R> from(Class<R> rootCls) {
        return RootSelectionImpl.of(getSelectionCls(), rootCls);
    }

}
