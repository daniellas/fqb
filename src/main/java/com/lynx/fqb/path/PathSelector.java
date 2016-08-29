package com.lynx.fqb.path;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

public interface PathSelector<A, B> extends Function<Path<?>, Path<?>>, Supplier<List<String>> {

    default <C> PathSelector<B, C> get(SingularAttribute<? super B, C> attr) {
        return new PathNode<B, C>(get(), attr);
    }
}
