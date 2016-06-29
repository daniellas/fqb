package com.lynx.fqb.select;

import com.lynx.fqb.api.Orders;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class OrderBy<R, F> implements Orders<R, F> {

    private final CriteriaBuilder cb;

    private final CriteriaQuery<R> cq;

    private final Root<F> root;

    public OrderBy(CriteriaBuilder cb, CriteriaQuery<R> cq, Root<F> root) {
        this.cb = cb;
        this.cq = cq;
        this.root = root;
    }

    @Override
    public CriteriaQuery<R> getCriteriaQuery() {
        return cq;
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return cb;
    }

    @Override
    public Root<F> getRoot() {
        return root;
    }

}
