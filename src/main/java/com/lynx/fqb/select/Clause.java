package com.lynx.fqb.select;

import com.lynx.fqb.api.CriteriaBuilderHolder;
import com.lynx.fqb.api.CriteriaQueryHolder;
import com.lynx.fqb.api.RootHolder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public abstract class Clause<R, F> implements CriteriaBuilderHolder, CriteriaQueryHolder<R>, RootHolder<F> {

    private final CriteriaBuilder cb;

    private final CriteriaQuery<R> cq;

    private final Root<F> root;

    public Clause(CriteriaBuilder cb, CriteriaQuery<R> cq, Root<F> root) {
        this.cb = cb;
        this.cq = cq;
        this.root = root;
    }

    @Override
    public Root<F> getRoot() {
        return root;
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
