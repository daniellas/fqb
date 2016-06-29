package com.lynx.fqb;

import com.lynx.fqb.api.PathSelector;

import javax.persistence.metamodel.SingularAttribute;

public class Paths {

    public static <F, A> PathSelector<F, A> get(SingularAttribute<? super F, A> attr) {
        return (p) -> {
            return p.get(attr);
        };
    }
}
