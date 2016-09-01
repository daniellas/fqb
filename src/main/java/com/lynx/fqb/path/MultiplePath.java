package com.lynx.fqb.path;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import com.lynx.fqb.Trail;

public class MultiplePath<A, B> extends Trail<PathSelector<A, B>, PathSelector<A, B>> implements MultiplePathApplier<A, B> {

    protected MultiplePath(List<PathSelector<A, B>> items, PathSelector<A, B> element) {
        super(items, element);
    }

    @Override
    protected Function<PathSelector<A, B>, PathSelector<A, B>> converter() {
        return a -> a;
    }

    @Override
    public List<Selection<?>> apply(Root<?> root) {
        return get().stream()
                .map(p -> p.apply(root))
                .collect(Collectors.toList());
    }

}
