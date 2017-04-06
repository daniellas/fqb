package com.lynx.fqb.select.impl;

import com.lynx.fqb.select.ResultSelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class ResultSelectionImpl<S, R> implements ResultSelection<S> {

    @Getter
    private final Class<S> selectionCls;

}
