package com.lynx.fqb.select;

import com.lynx.fqb.api.select.Selections;

import javax.persistence.criteria.CriteriaBuilder;

public class Select<R, F> implements Selections<R, F> {

    private final CriteriaBuilder cb;

    public Select(CriteriaBuilder cb) {
        this.cb = cb;
    }

    @Override
    public CriteriaBuilder getCriteriaBuilder() {
        return cb;
    }

}
