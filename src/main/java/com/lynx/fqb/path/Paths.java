package com.lynx.fqb.path;

import javax.persistence.metamodel.SingularAttribute;

public abstract class Paths {

    public static <F, A> PathSelector<F, A> get(SingularAttribute<? super F, A> attr) {
        return (p) -> {
            return p.get(attr);
        };
    }
}
