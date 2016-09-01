package com.lynx.fqb.path;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import com.lynx.fqb.Trail;

public class MultiplePath extends Trail<PathSelector<?, ?>, PathSelector<?, ?>> implements MultiplePathApplier {

    protected MultiplePath(List<PathSelector<?, ?>> items, PathSelector<?, ?> element) {
        super(items, element);
    }

    @Override
    protected Function<PathSelector<?, ?>, PathSelector<?, ?>> converter() {
        return a -> a;
    }

    @Override
    public List<Path<?>> apply(Root<?> root) {
        return get().stream()
                .map(p -> p.apply(root))
                .collect(Collectors.toList());
    }

}
