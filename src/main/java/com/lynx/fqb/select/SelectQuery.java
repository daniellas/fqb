package com.lynx.fqb.select;

import com.lynx.fqb.api.Selections;

import javax.persistence.criteria.CriteriaBuilder;

public class SelectQuery<R, F> implements Selections<R, F> {

    private final CriteriaBuilder cb;

    public SelectQuery(CriteriaBuilder cb) {
        this.cb = cb;

    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return cb;
    }

}
