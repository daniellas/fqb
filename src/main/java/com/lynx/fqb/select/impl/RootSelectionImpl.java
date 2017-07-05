package com.lynx.fqb.select.impl;

import com.lynx.fqb.intercept.PredicatesInterceptor;
import com.lynx.fqb.select.RootSelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class RootSelectionImpl<S, R> implements RootSelection<S, R> {

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

    private final PredicatesInterceptor<R> predicatesInterceptor;

}
