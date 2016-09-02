package com.lynx.fqb.path;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public interface MultiplePathProvider extends Function<Root<?>, List<Path<?>>>, Supplier<List<PathSelector<?, ?>>> {

    default <A, B> MultiplePathProvider and(PathSelector<A, B> pathSelector) {
        return new MultiplePath(get(), pathSelector);
    }

    default <A, B> MultiplePathProvider and(SingularAttribute<A, B> attr) {
        return and(Paths.get(attr));
    }

}
