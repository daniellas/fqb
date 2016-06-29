package com.lynx.fqb.select;

import com.lynx.fqb.api.Groupings;
import com.lynx.fqb.api.Havings;
import com.lynx.fqb.api.Orders;
import com.lynx.fqb.api.Restrictions;
import com.lynx.fqb.api.Results;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Source<R, F> implements Restrictions<R, F>, Groupings, Havings, Orders<R, F>, Results<R> {

    private final CriteriaBuilder cb;
    private final CriteriaQuery<R> cq;
    private final Root<F> root;

    public Source(CriteriaBuilder cb, CriteriaQuery<R> cq, Root<F> root) {
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
