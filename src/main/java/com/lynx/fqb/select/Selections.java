package com.lynx.fqb.select;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.metamodel.SingularAttribute;

import com.lynx.fqb.combinator.Combinators;
import com.lynx.fqb.path.Paths;

public interface Selections {

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> ofAttributes(SingularAttribute<R, ?>... attrs) {
        return (cb, root) -> {
            return Arrays.stream(attrs)
                    .map(a -> Paths.get(a).apply(root))
                    .toArray(Selection<?>[]::new);
        };
    }

    @SafeVarargs
    public static <R> BiFunction<CriteriaBuilder, Root<R>, Selection<?>[]> ofPaths(Function<Path<R>, ? extends Selection<?>>... selections) {
        return Combinators.fromFunctionList(selections, Selection<?>[]::new);
    }

}
