package com.lynx.fqb.select.impl;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.select.EntitySelection;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class EntitySelectionImpl<S> implements EntitySelection<S> {

    @Getter
    private final Boolean distinct;
    
    private final Class<S> selectionCls;

    @Override
    public Optional<BiFunction<CriteriaBuilder, Root<S>, Selection<?>[]>> getSelections() {
        return Optional.empty();
    }

    @Override
    public Class<S> getRootCls() {
        return selectionCls;
    }

}
