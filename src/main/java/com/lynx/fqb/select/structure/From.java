package com.lynx.fqb.select.structure;

import com.lynx.fqb.api.select.Sources;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

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
