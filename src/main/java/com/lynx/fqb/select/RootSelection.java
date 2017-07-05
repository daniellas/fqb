package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.intercept.PredicatesInterceptor;
import com.lynx.fqb.select.impl.CustomSelectionImpl;

public interface RootSelection<S, R> {

    Class<S> getSelectionCls();

    Class<R> getRootCls();

    PredicatesInterceptor<R> getPredicatesInterceptor();

    default CustomSelection<S, R> with(BiFunction<CriteriaBuilder, Path<? extends R>, Selection<?>[]> selections) {
        return CustomSelectionImpl.of(getSelectionCls(), getRootCls(), selections, getPredicatesInterceptor());
    }

}
