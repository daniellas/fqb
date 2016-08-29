package com.lynx.fqb.sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;

import com.lynx.fqb.path.PathSelector;

public class PathSelectorSort<F, A, B> implements SortApplier {

    private final PathSelector<A, B> pathSelector;

    public PathSelectorSort(PathSelector<A, B> pathSelector) {
        this.pathSelector = pathSelector;
    }

    @Override
    public Order apply(CriteriaBuilder cb, Path<?> path) {
        return cb.asc(pathSelector.apply(path));
    }

}
