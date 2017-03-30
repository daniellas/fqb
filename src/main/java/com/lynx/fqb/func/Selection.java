package com.lynx.fqb.func;

import static com.lynx.fqb.func.QueryExecutor.*;

public interface Selection<S> extends Source<S> {

    default <R> Result<R> from(Class<R> resultCls) {
        return em -> {
            return getCriteriaBuilder()
                    .andThen(createCriteriaQuery(resultCls))
                    .andThen(applyRoot(resultCls))
                    .andThen(applySelection())
                    .andThen(createTypedQuery(em))
                    .apply(em);
        };
    }

}
