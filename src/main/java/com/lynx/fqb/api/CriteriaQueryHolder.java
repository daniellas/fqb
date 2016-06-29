package com.lynx.fqb.api;

import javax.persistence.criteria.CriteriaQuery;

public interface CriteriaQueryHolder<R> {

    CriteriaQuery<R> getCriteriaQuery();
}
