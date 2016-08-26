package com.lynx.fqb.old.api.select;

import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import com.lynx.fqb.old.api.CriteriaBuilderHolder;
import com.lynx.fqb.old.api.CriteriaQueryHolder;
import com.lynx.fqb.old.api.RootHolder;

public interface Restrictions<R, F> extends CriteriaBuilderHolder, CriteriaQueryHolder<R>, RootHolder<F> {

    @SuppressWarnings("unchecked")
    default Results<R> where(Function<CriteriaBuilder, Predicate>... restrictions) {
        return null;
    }

}
