package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.lynx.fqb.select.impl.WhereImpl;

public interface Join<S, R> extends Where<S, R> {

    default Where<S, R> where(BiFunction<CriteriaBuilder, Root<R>, Predicate[]> restrictions) {
        return WhereImpl.of(getSelectionCls(), getRootCls(), getSelections(), restrictions, getPredicatesInterceptor());
    }

}
