package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

import com.lynx.fqb.select.impl.WhereImpl;

public interface Join<S, R> extends Where<S, R> {

    default Where<S, R> where(BiFunction<CriteriaBuilder, Path<R>, Predicate[]> restrictions) {
        return WhereImpl.of(getSelectionCls(), getRootCls(), getSelections(), getJoins(), restrictions, getPredicatesInterceptor());
    }

}
