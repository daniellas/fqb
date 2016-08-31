package com.lynx.fqb.sort;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.PathSelector;

/**
 * Sorts factoring class
 * 
 * @author daniel.las
 *
 */
public class Sorts {

    private Sorts() {

    }

    public static <F, A> MultipleSortApplier attribute(SingularAttribute<? super F, A> attribute) {
        return new MultipleSort(null, by(attribute));
    }

    public static <A, B> MultipleSortApplier path(PathSelector<A, B> pathSelector) {
        return new MultipleSort(null, by(pathSelector));
    }

    public static <F, A> SortApplier by(SingularAttribute<? super F, A> attribute) {
        return new AttributeSort<>(attribute);
    }

    public static <F, A, B> SortApplier by(PathSelector<A, B> pathSelector) {
        return new PathSelectorSort<F, A, B>(pathSelector);
    }

    /**
     * Creates multiple sorts in conjunction with {@link #by(PathSelector)} and
     * {@link #by(SingularAttribute)} methods
     * 
     * @param applier
     * 
     * @return
     */
    public static MultipleSortApplier sorts(BiFunction<CriteriaBuilder, Path<?>, Order> applier) {
        return new MultipleSort(null, applier);
    }

}
