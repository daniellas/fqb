package com.lynx.fqb.sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import com.lynx.fqb.path.PathSelector;

public class PathSelectorSort<F, A, B> implements SortApplier {

    final PathSelector<A, B> pathSelector;

    public PathSelectorSort(PathSelector<A, B> pathSelector) {
        this.pathSelector = pathSelector;
    }

    @Override
    public Order apply(CriteriaBuilder cb, Root<?> root) {
        return cb.asc(pathSelector.apply(root));
    }

}
