package com.lynx.fqb.sort;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

public interface SortApplier extends BiFunction<CriteriaBuilder, Path<?>, Order> {

    default BiFunction<CriteriaBuilder, Path<?>, Order> reversed() {
        return andThen((o) -> {
            return o.reverse();
        });
    }

}
