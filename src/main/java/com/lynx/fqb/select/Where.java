package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import com.lynx.fqb.select.impl.GroupByImpl;

public interface Where<S, R> extends GroupBy<S, R> {

    default GroupBy<S, R> groupBy(BiFunction<CriteriaBuilder, Root<R>, Expression<?>[]> groupings) {
        return GroupByImpl.of(getSelectionCls(), getRootCls(), getSelections(), getRestrictions(), groupings, getPredicatesInterceptor());
    }
}
