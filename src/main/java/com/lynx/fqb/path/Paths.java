package com.lynx.fqb.path;

import java.util.List;
import java.util.Set;
import java.util.function.Function;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.metamodel.PluralAttribute;
import javax.persistence.metamodel.SingularAttribute;

/**
 * {@link Path} traversing/accessing functions
 * 
 * @author Daniel Łaś
 *
 */
public interface Paths {

    /**
     * Access {@link Path} by given attribute
     * 
     * @param attr
     *            of required path
     * @return path selecting function as {@link PathSelector}
     */
    public static <A, B> PathSelector<A, B> get(SingularAttribute<? super A, B> attr) {
        return path -> {
            return path.get(attr);
        };
    }

    public static <A, B> Function<Path<A>, Expression<List<B>>> getList(PluralAttribute<A, List<B>, B> coll) {
        return path -> {
            return path.get(coll);
        };
    }

    public static <A, B> Function<Path<A>, Expression<Set<B>>> getSet(PluralAttribute<A, Set<B>, B> coll) {
        return path -> {
            return path.get(coll);
        };
    }

    /**
     * Path selecting interface with additional functions simplifying paths
     * access
     * 
     * @author Daniel Łaś
     *
     * @param <A>
     * @param <B>
     */
    public interface PathSelector<A, B> extends Function<Path<A>, Path<B>> {
        /**
         * Gets nested path by given attribute
         * 
         * @param attr
         *            to return path for
         * @return path selecting function
         */
        default <C> Function<Path<A>, Path<C>> get(SingularAttribute<? super B, C> attr) {
            return andThen(Paths.get(attr));
        }
    }

}
