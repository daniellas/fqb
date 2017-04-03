package com.lynx.fqb.func.clause.impl;

import com.lynx.fqb.func.clause.RootSelection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class RootSelectionImpl<S, R> implements RootSelection<S, R> {

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

}
