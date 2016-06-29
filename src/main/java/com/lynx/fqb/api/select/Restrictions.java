package com.lynx.fqb.api.select;

import com.lynx.fqb.api.CriteriaBuilderHolder;
import com.lynx.fqb.api.CriteriaQueryHolder;
import com.lynx.fqb.api.RootHolder;

import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

public interface Restrictions<R, F> extends CriteriaBuilderHolder, CriteriaQueryHolder<R>, RootHolder<F> {

    @SuppressWarnings("unchecked")
    default Results<R> where(Function<CriteriaBuilder, Predicate>... restrictions) {
        return null;
    }

}
