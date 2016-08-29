package com.lynx.fqb.sort;

import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.PathSelector;

public abstract class Sorts {

    public static <F, A> SortApplier by(SingularAttribute<? super F, A> attribute) {
        return new AttributeSort<>(attribute);
    }

    public static <F, A, B> SortApplier by(PathSelector<A, B> pathSelector) {
        return new PathSelectorSort<F, A, B>(pathSelector);
    }

}
