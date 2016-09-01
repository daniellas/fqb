package com.lynx.fqb.path;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public interface MultiplePathApplier extends Function<Root<?>, List<Path<?>>>, Supplier<List<PathSelector<?, ?>>> {

    default <A, B> MultiplePathApplier and(PathSelector<A, B> pathSelector) {
        return new MultiplePath(get(), pathSelector);
    }

    default <A, B> MultiplePathApplier and(SingularAttribute<A, B> attr) {
        return and(Paths.get(attr));
    }

}
