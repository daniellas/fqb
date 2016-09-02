package com.lynx.fqb.sort;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.PathSelector;

/**
 * Sorts factoring class
 * 
 * @author daniel.las
 *
 */
public abstract class Sorts {

    protected Sorts() {

    }

    public static <F, A> MultipleSortProvider attribute(SingularAttribute<? super F, A> attribute) {
        return new MultipleSort(null, by(attribute));
    }

    public static <A, B> MultipleSortProvider path(PathSelector<A, B> pathSelector) {
        return new MultipleSort(null, by(pathSelector));
    }

    public static <F, A> SortProvider by(SingularAttribute<? super F, A> attribute) {
        return new AttributeSort<>(attribute);
    }

    public static <F, A, B> SortProvider by(PathSelector<A, B> pathSelector) {
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
    public static MultipleSortProvider sorts(BiFunction<CriteriaBuilder, Root<?>, Order> applier) {
        return new MultipleSort(null, applier);
    }

}
