package com.lynx.fqb.select;

import com.lynx.fqb.api.Results;

import javax.persistence.criteria.CriteriaQuery;

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
