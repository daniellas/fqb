package com.lynx.fqb.old.select;

import javax.persistence.criteria.CriteriaBuilder;

import com.lynx.fqb.old.api.select.Selections;

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
