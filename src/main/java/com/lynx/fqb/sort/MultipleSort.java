package com.lynx.fqb.sort;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import com.lynx.fqb.Trail;

public class MultipleSort extends Trail<BiFunction<CriteriaBuilder, Path<?>, Order>, BiFunction<CriteriaBuilder, Path<?>, Order>>
        implements MultipleSortApplier {

    protected MultipleSort(List<BiFunction<CriteriaBuilder, Path<?>, Order>> items, BiFunction<CriteriaBuilder, Path<?>, Order> element) {
        super(items, element);
    }

    @Override
    protected Function<BiFunction<CriteriaBuilder, Path<?>, Order>, BiFunction<CriteriaBuilder, Path<?>, Order>> converter() {
        return a -> a;
    }

    @Override
    public List<Order> apply(CriteriaBuilder cb, Path<?> path) {
        return get().stream().map(a -> a.apply(cb, path)).collect(Collectors.toList());
    }

}
