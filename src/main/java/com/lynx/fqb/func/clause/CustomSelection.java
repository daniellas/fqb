package com.lynx.fqb.func.clause;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public interface CustomSelection<S, R> extends Join<S> {

    BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> getSelections();

    default Join<S> join() {
        return em -> {
            return null;
        };
    }

}
