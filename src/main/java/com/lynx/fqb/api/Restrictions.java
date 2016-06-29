package com.lynx.fqb.api;

import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public interface Restrictions<R, F> extends CriteriaBuilderHolder, CriteriaQueryHolder<R>, RootHolder<F> {

    @SuppressWarnings("unchecked")
    default Results<R> where(Function<CriteriaBuilder, Predicate>... restrictions) {
        return null;
    }

}
