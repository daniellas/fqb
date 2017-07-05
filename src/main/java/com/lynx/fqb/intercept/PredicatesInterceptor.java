package com.lynx.fqb.intercept;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

@FunctionalInterface
public interface PredicatesInterceptor<R> {

    Predicate[] apply(CriteriaBuilder cb, Path<R> root, Predicate[] predicates);

    public static <R> PredicatesInterceptor<R> identity() {
        return (cb, root, predicates) -> predicates;
    }
}
