package com.lynx.fqb.func;

import static com.lynx.fqb.func.QueryExecutor.*;

public class Select implements QueryExecutor {

    public static <S> Selection<S> entity(Class<S> selectionCls) {
        return em -> {
            return getCriteriaBuilder()
                    .andThen(createCriteriaQuery(selectionCls))
                    .andThen(applyRoot(selectionCls))
                    .andThen(applySelection())
                    .andThen(createTypedQuery(em))
                    .apply(em);
        };
    }

    public static <R> Source<R> from(Class<R> rootCls) {
        return entity(rootCls);
    }
}
