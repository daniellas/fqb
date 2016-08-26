package com.lynx.fqb.old.select.structure;

import javax.persistence.criteria.CriteriaBuilder;

import com.lynx.fqb.old.api.select.Selections;

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
