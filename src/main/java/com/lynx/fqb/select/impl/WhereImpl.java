package com.lynx.fqb.select.impl;

import java.util.Optional;
import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.select.Where;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "of")
public class WhereImpl<S, R> implements Where<S, R> {

    @Getter
    private final Class<S> selectionCls;

    @Getter
    private final Class<R> rootCls;

    @Getter
    private final Optional<BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]>> selections;

    private final BiFunction<CriteriaBuilder, Root<R>, Predicate[]> restrictions;

    @Override
    public Optional<BiFunction<CriteriaBuilder, Root<R>, Predicate[]>> getRestrictions() {
        return Optional.of(restrictions);
    };


}