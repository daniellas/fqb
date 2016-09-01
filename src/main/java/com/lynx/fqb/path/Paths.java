package com.lynx.fqb.path;

import javax.persistence.metamodel.SingularAttribute;

public abstract class Paths {

    protected Paths() {

    }

    public static <A, B> PathSelector<A, B> get(SingularAttribute<? super A, B> attr) {
        return new PathNode<>(null, attr);
    }

}
