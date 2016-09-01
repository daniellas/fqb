package com.lynx.fqb.sort;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.lynx.fqb.Trail;

public class MultipleSort extends Trail<BiFunction<CriteriaBuilder, Root<?>, Order>, BiFunction<CriteriaBuilder, Root<?>, Order>>
        implements MultipleSortApplier {

    protected MultipleSort(List<BiFunction<CriteriaBuilder, Root<?>, Order>> items, BiFunction<CriteriaBuilder, Root<?>, Order> element) {
        super(items, element);
    }

    @Override
    protected Function<BiFunction<CriteriaBuilder, Root<?>, Order>, BiFunction<CriteriaBuilder, Root<?>, Order>> converter() {
        return a -> a;
    }

    @Override
    public List<Order> apply(CriteriaBuilder cb, Root<?> root) {
        return get().stream().map(a -> a.apply(cb, root)).collect(Collectors.toList());
    }

}
