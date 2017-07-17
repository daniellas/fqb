package com.lynx.fqb;

import javax.persistence.Tuple;

import com.lynx.fqb.intercept.PredicatesInterceptor;
import com.lynx.fqb.select.EntitySelection;
import com.lynx.fqb.select.RootSelection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Select criteria query functional builder
 * 
 * @author dalas0
 *
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Select {

    /**
     * Select from given root class
     * 
     * @param rootCls
     *            root class
     * @return {@link EntitySelection} interface
     * @param <R>
     *            selection root type
     */
    public static <R> EntitySelection<R> from(Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).from(rootCls);
    }

    /**
     * Select distinct from given root class
     * 
     * @param rootCls
     *            root class
     * @return {@link EntitySelection} interface
     * @param <R>
     *            selection root type
     */
    public static <R> EntitySelection<R> distinct(Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).distinct(rootCls);
    }

    /**
     * Select custom result from given root class
     * 
     * @param selectionCls
     *            selection result class
     * @param rootCls
     *            root class
     * @return {@link RootSelection} interface
     * @param <S>
     *            selection result type
     * @param <R>
     *            selection root type
     */
    public static <S, R> RootSelection<S, R> customFrom(Class<S> selectionCls, Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).customFrom(selectionCls, rootCls);
    }

    /**
     * Select {@link Tuple} from given root class
     * 
     * @param rootCls
     *            root class
     * @return {@link RootSelection} interface
     * @param <R>
     *            selection root type
     */
    public static <R> RootSelection<Tuple, R> tupleFrom(Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).tupleFrom(rootCls);
    }

    /**
     * Intercepting version of select clause
     * 
     * @author dalas0
     *
     * @param <R>
     *            selection root type
     */
    @RequiredArgsConstructor
    public static class InterceptingSelect<R> {

        private final PredicatesInterceptor<R> predicatesInterceptor;

        /**
         * Select from given root class
         * 
         * @param rootCls
         *            root class
         * @return {@link EntitySelection} interface
         * @param <R>
         *            selection root type
         */
        public EntitySelection<R> from(Class<R> rootCls) {
            return new EntitySelection<>(false, rootCls, predicatesInterceptor);
        }

        /**
         * Select distinct from given root class
         * 
         * @param rootCls
         *            root class
         * @return {@link EntitySelection} interface
         * @param <R>
         *            selection root type
         */
        public EntitySelection<R> distinct(Class<R> rootCls) {
            return new EntitySelection<>(true, rootCls, predicatesInterceptor);
        }

        /**
         * Select custom result from given root class
         * 
         * @param selectionCls
         *            selection result class
         * @param rootCls
         *            root class
         * @return {@link RootSelection} interface
         * @param <S>
         *            selection result type
         * @param <R>
         *            selection root type
         */
        public <S> RootSelection<S, R> customFrom(Class<S> selectionCls, Class<R> rootCls) {
            return new RootSelection<>(selectionCls, rootCls, predicatesInterceptor);
        }

        /**
         * Select {@link Tuple} from given root class
         * 
         * @param rootCls
         *            root class
         * @return {@link RootSelection} interface
         * @param <R>
         *            selection root type
         */
        public RootSelection<Tuple, R> tupleFrom(Class<R> rootCls) {
            return customFrom(Tuple.class, rootCls);
        }

    }
}
