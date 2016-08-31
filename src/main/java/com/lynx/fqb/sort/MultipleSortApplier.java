package com.lynx.fqb.sort;

import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

public interface MultipleSortApplier
        extends BiFunction<CriteriaBuilder, Path<?>, Collection<Order>>, Supplier<List<BiFunction<CriteriaBuilder, Path<?>, Order>>> {

    default MultipleSortApplier then(BiFunction<CriteriaBuilder, Path<?>, Order> applier) {
        return new MultipleSort(get(), applier);
    }

}
