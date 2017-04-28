package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;

import com.lynx.fqb.select.impl.GroupByImpl;

public interface Where<S, R> extends GroupBy<S, R> {

    default GroupBy<S, R> groupBy(BiFunction<CriteriaBuilder, Path<R>, Expression<?>[]> groupings) {
        return GroupByImpl.of(getSelectionCls(), getRootCls(), getSelections(), getJoins(), getRestrictions(), groupings, getPredicatesInterceptor());
    }
}
