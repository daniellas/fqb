package com.lynx.fqb.select.ctx;

import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 
 * @author daniel.las
 *
 * @param <F>
 *            from class
 */
public interface SourceContext<F> extends QueryContext {

    /**
     * Returns query root
     * 
     * @return root
     */
    Root<F> getRoot();

    /**
     * Applies parameters on {@link CriteriaQuery} and returns it as
     * {@link Optional}
     * 
     * @return {@link CriteriaQuery} {@link Optional}
     */
    Optional<CriteriaQuery<F>> doApply();
}
