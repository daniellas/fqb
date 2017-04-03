package com.lynx.fqb.path;

import java.util.function.Function;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

public interface Paths {

    public static <A, B> Function<Path<A>, Path<B>> get(SingularAttribute<? super A, B> attr) {
        return p -> {
            return p.get(attr);
        };
    }

}
