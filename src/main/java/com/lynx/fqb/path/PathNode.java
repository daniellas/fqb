package com.lynx.fqb.path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

public class PathNode<A, B> implements PathSelector<A, B> {

    private List<String> attributePath;

    public PathNode(List<String> attributePath, SingularAttribute<? super A, B> attr) {
        this.attributePath = Optional.ofNullable(attributePath).map(p -> {
            p.add(attr.getName());

            return p;
        }).orElse(new ArrayList<>(Arrays.asList(attr.getName())));
    }

    @Override
    public Path<?> apply(Path<?> root) {
        return doApply(root, attributePath.get(0), 0);
    }

    private Path<?> doApply(Path<?> path, String attr, int idx) {
        if (idx < attributePath.size() - 1) {
            return doApply(path.get(attr), attributePath.get(idx + 1), idx + 1);
        }

        return path.get(attr);
    }

    @Override
    public List<String> get() {
        return attributePath;
    }

}
