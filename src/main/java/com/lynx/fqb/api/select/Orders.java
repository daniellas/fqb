package com.lynx.fqb.api.select;

import com.lynx.fqb.OptionalApplier;
import com.lynx.fqb.api.CriteriaBuilderHolder;
import com.lynx.fqb.api.CriteriaQueryHolder;
import com.lynx.fqb.api.RootHolder;
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

    default Result<R> orderBy(BiFunction<CriteriaBuilder, Path<F>, Order> applier) {
        getCriteriaQuery().orderBy(applier.apply(getCriteriaBuilder(), getRoot()));

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderBy(Stream<BiFunction<CriteriaBuilder, Path<F>, Order>> appliers) {
        getCriteriaQuery().orderBy(
                appliers
                        .map(s -> s.apply(getCriteriaBuilder(), getRoot()))
                        .collect(Collectors.toList()));

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderBy(Collection<BiFunction<CriteriaBuilder, Path<F>, Order>> appliers) {
        return orderBy(appliers.stream());
    }

    default Result<R> orderByOptional(Optional<BiFunction<CriteriaBuilder, Path<F>, Order>> applier) {
        OptionalApplier.applyOptionalBiFunction(applier, getCriteriaBuilder(), getRoot(), (r) -> {
            getCriteriaQuery().orderBy(r);
        });

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderByOptional(Stream<Optional<BiFunction<CriteriaBuilder, Path<F>, Order>>> appliers) {
        OptionalApplier.applyOptionalBiFunctionStream(appliers, getCriteriaBuilder(), getRoot(), (c) -> {
            getCriteriaQuery().orderBy(c);
        });

        return new Result<>(getCriteriaQuery());
    }

    default Result<R> orderByOptional(Collection<Optional<BiFunction<CriteriaBuilder, Path<F>, Order>>> suppliers) {
        return orderByOptional(suppliers.stream());
    }

}
