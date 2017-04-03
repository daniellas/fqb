package com.lynx.fqb.select.impl;

import com.lynx.fqb.select.RootSelection;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(staticName = "of")
public class RootSelectionImpl<S, R> implements RootSelection<S, R> {

    private final Class<S> selectionCls;

    private final Class<R> rootCls;

}
