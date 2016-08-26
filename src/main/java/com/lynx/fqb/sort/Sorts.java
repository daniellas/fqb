package com.lynx.fqb.sort;

import java.util.function.Function;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

public abstract class Sorts {

    public static <F, A> SortApplier<F> by(SingularAttribute<? super F, A> attribute) {
        return (cb, path) -> {
            return cb.asc(path.get(attribute));
        };
    }

    public static <F, A> SortApplier<F> by(Function<Path<F>, Path<A>> pathSelector) {
        return (cb, p) -> {
            return cb.asc(pathSelector.apply(p));
        };
    }

}
