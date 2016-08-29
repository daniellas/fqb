package com.lynx.fqb.sort;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

public abstract class Sorts {

    public static <F, A> SortApplier<F> by(SingularAttribute<? super F, A> attribute) {
        return (cb, path) -> {
            return cb.asc(path.get(attribute));
        };
    }

    public static <F, A> SortApplier<F> by(Path<?> path) {
        return (cb, p) -> {
            return cb.asc(path);
        };
    }

}
