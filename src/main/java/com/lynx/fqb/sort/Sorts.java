package com.lynx.fqb.sort;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.PathSelector;

public class Sorts {

    private Sorts() {

    }

    public static <F, A> SortApplier by(SingularAttribute<? super F, A> attribute) {
        return new AttributeSort<>(attribute);
    }

    public static <F, A> MultipleSortApplier byAttribute(SingularAttribute<? super F, A> attribute) {
        return new MultipleSort(null, by(attribute));
    }

    public static <F, A, B> SortApplier by(PathSelector<A, B> pathSelector) {
        return new PathSelectorSort<F, A, B>(pathSelector);
    }

    public static <A, B> MultipleSortApplier byPath(PathSelector<A, B> pathSelector) {
        return new MultipleSort(null, by(pathSelector));
    }

    public static MultipleSortApplier sorts(BiFunction<CriteriaBuilder, Path<?>, Order> applier) {
        return new MultipleSort(null, applier);
    }

}
