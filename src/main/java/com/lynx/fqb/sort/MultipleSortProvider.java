package com.lynx.fqb.sort;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public interface MultipleSortProvider
        extends BiFunction<CriteriaBuilder, Root<?>, List<Order>>, Supplier<List<BiFunction<CriteriaBuilder, Root<?>, Order>>> {

    default MultipleSortProvider then(BiFunction<CriteriaBuilder, Root<?>, Order> applier) {
        return new MultipleSort(get(), applier);
    }

}
