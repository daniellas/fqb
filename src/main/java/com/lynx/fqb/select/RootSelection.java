package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.select.impl.CustomSelectionImpl;

public interface RootSelection<S, R> {

    Class<S> getSelectionCls();

    Class<R> getRootCls();

    default Join<S,R> with(BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> selections) {
        return CustomSelectionImpl.of(getSelectionCls(), getRootCls(), selections);
    }

}
