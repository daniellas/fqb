package com.lynx.fqb.old.select.structure;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.lynx.fqb.old.api.select.Sources;

public class From<R, F> implements Sources<R, F> {

    private final CriteriaBuilder cb;

    private final CriteriaQuery<R> cq;

    public From(CriteriaBuilder cb, CriteriaQuery<R> cq) {
        this.cq = cq;
        this.cb = cb;
    }

    @Override
    public CriteriaQuery<R> getCriteriaQuery() {
        return cq;
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return cb;
    }

}
