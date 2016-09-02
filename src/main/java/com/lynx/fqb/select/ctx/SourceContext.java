package com.lynx.fqb.select.ctx;

import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public interface SourceContext<F> extends QueryContext {

    Root<F> getRoot();

    Optional<CriteriaQuery<F>> doApply();
}
