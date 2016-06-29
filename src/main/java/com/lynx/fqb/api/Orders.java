package com.lynx.fqb.api;

import com.lynx.fqb.select.Result;

import java.util.Collection;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

public interface Orders<R, F> extends CriteriaBuilderHolder, CriteriaQueryHolder<R>, RootHolder<F> {

    default Result<R> orderBy(BiFunction<CriteriaBuilder, Path<F>, Order> supplier) {
        getCriteriaQuery().orderBy(supplier.apply(getCriteriaBuilder(), getRoot()));

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderBy(Stream<BiFunction<CriteriaBuilder, Path<F>, Order>> suppliers) {
        getCriteriaQuery().orderBy(
                suppliers
                        .map(s -> s.apply(getCriteriaBuilder(), getRoot()))
                        .collect(Collectors.toList()));

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderBy(Collection<BiFunction<CriteriaBuilder, Path<F>, Order>> suppliers) {
        return orderBy(suppliers.stream());
    }

    default Result<R> orderByOptional(Optional<BiFunction<CriteriaBuilder, Path<F>, Order>> supplier) {
        supplier.ifPresent((s) -> {
            getCriteriaQuery().orderBy(supplier.get().apply(getCriteriaBuilder(), getRoot()));
        });

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderByOptional(Stream<Optional<BiFunction<CriteriaBuilder, Path<F>, Order>>> suppliers) {
        getCriteriaQuery()
                .orderBy(suppliers.filter(s -> s.isPresent())
                        .map(s -> s.get().apply(getCriteriaBuilder(), getRoot()))
                        .collect(Collectors.toList()));

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderByOptional(Collection<Optional<BiFunction<CriteriaBuilder, Path<F>, Order>>> suppliers) {
        return orderByOptional(suppliers.stream());
    }

}
