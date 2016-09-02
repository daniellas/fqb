package com.lynx.fqb.sort;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public interface SortProvider extends BiFunction<CriteriaBuilder, Root<?>, Order> {

    default BiFunction<CriteriaBuilder, Root<?>, Order> reversed() {
        return andThen((o) -> {
            return o.reverse();
        });
    }

}
