package com.lynx.fqb.func;

import static com.lynx.fqb.func.QueryBuilder.*;

import java.util.function.Function;

import javax.persistence.Tuple;

import com.lynx.fqb.func.clause.Join;
import com.lynx.fqb.func.clause.ResultSelection;
import com.lynx.fqb.func.clause.Selection;
import com.lynx.fqb.func.clause.impl.ResultSelectionImpl;
import com.lynx.fqb.func.clause.impl.SelectionImpl;

public class Select {

    public static <S> ResultSelection<S> as(Class<S> selectionCls) {
        return ResultSelectionImpl.of(selectionCls);
    }

    public static ResultSelection<Tuple> tuple() {
        return as(Tuple.class);
    }

    public static <R> Selection<R> from(Class<R> rootCls) {
        return SelectionImpl.of(rootCls);
    }

    public static <S, R> RootSelector<S, R> asF(Class<S> selectionCls) {
        return rootCls -> {
            return em -> {

                getCriteriaBuilder()
                        .andThen(createCriteriaQuery(selectionCls))
                        .andThen(applyRoot(rootCls))
                        .andThen(createTypedQuery(em))
                        .apply(em);

                return null;
            };
        };
    }

    public static interface RootSelector<S, R> extends Function<Class<R>, Join<R>> {

        default Join<R> from(Class<R> rootCls) {
            return apply(rootCls);
        }
    }
}
