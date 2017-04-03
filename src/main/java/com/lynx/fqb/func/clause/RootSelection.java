package com.lynx.fqb.func.clause;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.func.clause.impl.CustomSelectionImpl;

public interface RootSelection<S, R> {

    Class<S> getSelectionCls();

    Class<R> getRootCls();

    default Join<S> with(BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> selections) {
        return CustomSelectionImpl.of(getSelectionCls(), getRootCls(), selections);
    }

}
