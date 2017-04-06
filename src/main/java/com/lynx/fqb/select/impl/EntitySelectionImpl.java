package com.lynx.fqb.select.impl;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.EntitySelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class EntitySelectionImpl<S> implements EntitySelection<S> {

    @Getter
    private final boolean distinct;

    private final Class<S> selectionCls;

    @Getter
    private final PredicatesInterceptor<S> predicatesInterceptor;

    @Override
    public Class<S> getRootCls() {
        return selectionCls;
    }

}
