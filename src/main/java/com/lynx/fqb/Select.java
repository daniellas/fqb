package com.lynx.fqb;

import javax.persistence.Tuple;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.EntitySelection;
import com.lynx.fqb.select.RootSelection;
import com.lynx.fqb.select.impl.EntitySelectionImpl;
import com.lynx.fqb.select.impl.RootSelectionImpl;

public class Select<R> {

    private final PredicatesInterceptor<R> predicatesInterceptor;

    public Select(PredicatesInterceptor<R> predicatesInterceptor) {
        this.predicatesInterceptor = predicatesInterceptor;
    }

    public static <R> EntitySelection<R> from(Class<R> rootCls) {
        return EntitySelectionImpl.of(false, rootCls, PredicatesInterceptor.identity());
    }

    public static <R> EntitySelection<R> distinct(Class<R> rootCls) {
        return EntitySelectionImpl.of(true, rootCls, PredicatesInterceptor.identity());
    }

    public static <S, R> RootSelection<S, R> customFrom(Class<S> selectionCls, Class<R> rootCls) {
        return RootSelectionImpl.of(false, selectionCls, rootCls, PredicatesInterceptor.identity());
    }

    public static <R> RootSelection<Tuple, R> tupleFrom(Class<R> rootCls) {
        return customFrom(Tuple.class, rootCls);
    }

    public EntitySelection<R> fromIntercepting(Class<R> rootCls) {
        return EntitySelectionImpl.of(false, rootCls, predicatesInterceptor);
    }

    public <S> RootSelection<S, R> customFromIntercepting(Class<S> selectionCls, Class<R> rootCls) {
        return RootSelectionImpl.of(false, selectionCls, rootCls, predicatesInterceptor);
    }

}
