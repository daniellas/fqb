package com.lynx.fqb;

import java.util.function.BiFunction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.metamodel.SingularAttribute;

public abstract class Predicates {

    public static <F, A> BiFunction<CriteriaBuilder, Path<F>, Predicate> equal(SingularAttribute<? super F, A> attr,
            Object value) {
        return (cb, root) -> {
            return cb.equal(root.get(attr), value);
        };
    }
}
