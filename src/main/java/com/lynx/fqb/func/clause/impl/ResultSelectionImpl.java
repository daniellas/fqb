package com.lynx.fqb.func.clause.impl;

import com.lynx.fqb.func.clause.ResultSelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ResultSelectionImpl<S> implements ResultSelection<S> {

    private final Class<S> selectionCls;

}
