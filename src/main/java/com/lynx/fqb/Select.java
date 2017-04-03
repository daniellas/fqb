package com.lynx.fqb;

import javax.persistence.Tuple;

import com.lynx.fqb.select.ResultSelection;
import com.lynx.fqb.select.EntitySelection;
import com.lynx.fqb.select.impl.ResultSelectionImpl;
import com.lynx.fqb.select.impl.EntitySelectionImpl;

public class Select {

    public static <R> EntitySelection<R> from(Class<R> rootCls) {
        return EntitySelectionImpl.of(rootCls);
    }

    public static <S> ResultSelection<S> as(Class<S> selectionCls) {
        return ResultSelectionImpl.of(selectionCls);
    }

    public static ResultSelection<Tuple> tuple() {
        return as(Tuple.class);
    }

}
