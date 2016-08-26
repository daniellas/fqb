package com.lynx.fqb.old.api;

import javax.persistence.criteria.CriteriaQuery;

public interface CriteriaQueryHolder<R> {

    CriteriaQuery<R> getCriteriaQuery();
}
