package com.lynx.fqb.predicate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface PredicatesInterceptor<R> {
    
    Predicate[] apply(CriteriaBuilder cb, Root<R> root, Predicate[] predicates);

    public static <R> PredicatesInterceptor<R> identity() {
        return (cb, root, predicates) -> {
            return predicates;
        };
    }
}
