package com.lynx.fqb;

import javax.persistence.Tuple;

import com.lynx.fqb.predicate.PredicatesInterceptor;
import com.lynx.fqb.select.EntitySelection;
import com.lynx.fqb.select.RootSelection;
import com.lynx.fqb.select.impl.EntitySelectionImpl;
import com.lynx.fqb.select.impl.RootSelectionImpl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class Select {

    public static <R> EntitySelection<R> from(Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).from(rootCls);
    }

    public static <R> EntitySelection<R> distinct(Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).distinct(rootCls);
    }

    public static <S, R> RootSelection<S, R> customFrom(Class<S> selectionCls, Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).customFrom(selectionCls, rootCls);
    }

    public static <R> RootSelection<Tuple, R> tupleFrom(Class<R> rootCls) {
        return new InterceptingSelect<R>(PredicatesInterceptor.identity()).tupleFrom(rootCls);
    }

    public static class InterceptingSelect<R> {

        private final PredicatesInterceptor<R> predicatesInterceptor;

        public InterceptingSelect(PredicatesInterceptor<R> predicatesInterceptor) {
            this.predicatesInterceptor = predicatesInterceptor;
        }

        public EntitySelection<R> from(Class<R> rootCls) {
            return EntitySelectionImpl.of(false, rootCls, predicatesInterceptor);
        }

        public EntitySelection<R> distinct(Class<R> rootCls) {
            return EntitySelectionImpl.of(true, rootCls, predicatesInterceptor);
        }

        public <S> RootSelection<S, R> customFrom(Class<S> selectionCls, Class<R> rootCls) {
            return RootSelectionImpl.of(selectionCls, rootCls, predicatesInterceptor);
        }

        public RootSelection<Tuple, R> tupleFrom(Class<R> rootCls) {
            return customFrom(Tuple.class, rootCls);
        }

    }
}
