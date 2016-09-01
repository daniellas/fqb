package com.lynx.fqb.path;

import java.util.List;
import java.util.function.Function;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.Trail;

public class PathNode<A, B> extends Trail<SingularAttribute<? super A, B>, String> implements PathSelector<A, B> {

    public PathNode(List<String> items, SingularAttribute<? super A, B> element) {
        super(items, element);
    }

    @Override
    public Path<?> apply(Path<?> root) {
        return doApply(root, get().get(0), 0);
    }

    private Path<?> doApply(Path<?> path, String attr, int idx) {
        if (idx < get().size() - 1) {
            return doApply(path.get(attr), get().get(idx + 1), idx + 1);
        }

        return path.get(attr);
    }

    @Override
    protected Function<SingularAttribute<? super A, B>, String> converter() {
        return a -> a.getName();
    }

}
