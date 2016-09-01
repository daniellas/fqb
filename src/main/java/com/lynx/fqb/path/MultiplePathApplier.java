package com.lynx.fqb.path;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

public interface MultiplePathApplier<A, B> extends Function<Root<?>, List<Selection<?>>>, Supplier<List<PathSelector<A, B>>> {

    default MultiplePathApplier<A, B> then(PathSelector<A, B> pathSelector) {
        return new MultiplePath<>(get(), pathSelector);
    }
}
