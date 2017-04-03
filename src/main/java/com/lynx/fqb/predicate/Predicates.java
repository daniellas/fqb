package com.lynx.fqb.predicate;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.path.Paths;

public interface Predicates {

    @SafeVarargs
    public static <T> BiFunction<CriteriaBuilder, Root<T>, Predicate[]> of(BiFunction<CriteriaBuilder, Root<T>, Predicate>... predicates) {
        return (cb, root) -> {
            return Arrays.stream(predicates).map(p -> p.apply(cb, root)).toArray(Predicate[]::new);
        };
    }

    public static <T, V> BiFunction<CriteriaBuilder, Root<T>, Predicate> equal(SingularAttribute<T, V> attr, V value) {
        return (cb, root) -> {
            return cb.equal(Paths.get(attr).apply(root), value);
        };
    }

    public static <T,V> BiFunction<CriteriaBuilder, Root<T>, Predicate> equal(Function<Path<T>, ? extends Path<V>> path, V value) {
        return (cb, root) -> {
            return cb.equal(path.apply(root), value);
        };
    }

}
