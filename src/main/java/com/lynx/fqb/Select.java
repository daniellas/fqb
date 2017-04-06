package com.lynx.fqb;

import javax.persistence.Tuple;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.EntitySelection;
import com.lynx.fqb.select.ResultSelection;
import com.lynx.fqb.select.impl.EntitySelectionImpl;
import com.lynx.fqb.select.impl.ResultSelectionImpl;

public class Select<R> {

    private final PredicatesInterceptor<R> predicatesInterceptor;

    public Select() {
        this.predicatesInterceptor = PredicatesInterceptor.identity();
    }

    public Select(PredicatesInterceptor<R> predicatesInterceptor) {
        this.predicatesInterceptor = predicatesInterceptor;
    }

    public static <R> EntitySelection<R> from(Class<R> rootCls) {
        return from(rootCls, PredicatesInterceptor.identity());
    }

    private static <R> EntitySelection<R> from(Class<R> rootCls, PredicatesInterceptor<R> predicatesInterceptor) {
        return EntitySelectionImpl.of(false, rootCls, predicatesInterceptor);
    }

    public static <R> EntitySelection<R> distinct(Class<R> rootCls) {
        return EntitySelectionImpl.of(true, rootCls, PredicatesInterceptor.identity());
    }

    private static <R> EntitySelection<R> distinct(Class<R> rootCls, PredicatesInterceptor<R> predicatesInterceptor) {
        return EntitySelectionImpl.of(true, rootCls, PredicatesInterceptor.identity());
    }

    public static <S> ResultSelection<S> as(Class<S> selectionCls) {
        return ResultSelectionImpl.of(selectionCls);
    }

    public static ResultSelection<Tuple> tuple() {
        return as(Tuple.class);
    }

    public EntitySelection<R> fromEntity(Class<R> rootCls) {
        return from(rootCls);
    }

    public EntitySelection<R> distinctEntity(Class<R> rootCls) {
        return distinct(rootCls);
    }

    public <S> ResultSelection<S> asCustom(Class<S> selectionCls) {
        return as(selectionCls);
    }

    public ResultSelection<Tuple> asTuple() {
        return tuple();
    }

}
