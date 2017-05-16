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

    public static <A, B> Function<Path<? extends A>, Path<B>> get(SingularAttribute<A, B> attr) {
        return path -> path.get(attr);
    }

}
