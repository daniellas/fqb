package com.lynx.fqb.sort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

public class AttributeSort<F, A> implements SortApplier<F> {

    private final SingularAttribute<? super F, A> attribute;

    public AttributeSort(SingularAttribute<? super F, A> attribute) {
        this.attribute = attribute;
    }

    @Override
    public Order apply(CriteriaBuilder cb, Path<F> path) {
        return cb.asc(path.get(attribute));
    }

}
