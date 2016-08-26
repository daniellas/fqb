package com.lynx.fqb.old.select.structure;

import javax.persistence.criteria.CriteriaQuery;

import com.lynx.fqb.old.api.select.Results;

public class Result<R> implements Results<R> {

    private final CriteriaQuery<R> cq;

    public Result(CriteriaQuery<R> cq) {
        this.cq = cq;
    }

    @Override
    public CriteriaQuery<R> getCriteriaQuery() {
        return cq;
    }

}
