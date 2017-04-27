package com.lynx.fqb.select;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;

import com.lynx.fqb.select.impl.JoinImpl;

public interface EntitySelection<R> extends Join<R, R> {

    default Join<R, R> join(BiFunction<CriteriaBuilder,From<R, R>, javax.persistence.criteria.Join<?, ?>[]> joins) {
        return JoinImpl.of(getSelectionCls(), getRootCls(), getSelections(), joins, getPredicatesInterceptor());
    }

}
