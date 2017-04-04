package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.lynx.fqb.select.impl.OrderByImpl;

public interface Having<S, R> extends OrderBy<S, R> {

    default OrderBy<S, R> orderBy(BiFunction<CriteriaBuilder, Root<R>, Order[]> orders) {
        return OrderByImpl.of(getSelectionCls(), getRootCls(), getSelections(), getRestrictions(), orders);
    }

}