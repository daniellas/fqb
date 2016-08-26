package com.lynx.fqb.old.api;

import java.util.function.Function;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.old.Paths;

public interface PathSelector<X, Y> extends Function<Path<X>, Path<Y>> {

    default <Z> Function<Path<Y>, Path<Z>> thenGet(SingularAttribute<? super Y, Z> attr) {
        return Paths.get(attr);
    }

}
