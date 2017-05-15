package com.lynx.fqb.path;

import java.util.function.Function;

import javax.persistence.criteria.Path;
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
    public static <A, B> Function<Path<A>, Path<B>> get(SingularAttribute<? super A, B> attr) {
        return path -> {
            return path.get(attr);
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
    public interface PathSelector<A, B> extends Function<Path<? super A>, Path<B>> {
        /**
         * Gets nested path by given attribute
         * 
         * @param attr
         *            to return path for
         * @return path selecting function
         */
        default <C> Function<Path<? super A>, Path<C>> get(SingularAttribute<B, C> attr) {
            return andThen(Paths.get(attr));
        }
    }

}
