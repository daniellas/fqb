package com.lynx.fqb.old;

import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.old.api.PathSelector;

public abstract class Paths {

    public static <F, A> PathSelector<F, A> get(SingularAttribute<? super F, A> attr) {
        return (p) -> {
            return p.get(attr);
        };
    }
}
