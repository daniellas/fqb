package com.lynx.fqb.select.ctx;

import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;

public interface SelectionContext<R> extends QueryContext {
    Optional<CriteriaQuery<R>> doApply();
}
