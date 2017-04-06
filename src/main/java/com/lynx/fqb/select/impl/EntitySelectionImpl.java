package com.lynx.fqb.select.impl;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.EntitySelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class EntitySelectionImpl<R> implements EntitySelection<R> {

    @Getter
    private final boolean distinct;

    private final Class<R> selectionCls;

    private final PredicatesInterceptor<R> predicatesInterceptor;

    @Override
    public Class<R> getRootCls() {
        return selectionCls;
    }

}
