package com.lynx.fqb.select.impl;

import com.lynx.fqb.select.ResultSelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ResultSelectionImpl<S> implements ResultSelection<S> {

    private final Class<S> selectionCls;

}
