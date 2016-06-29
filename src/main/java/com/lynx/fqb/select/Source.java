package com.lynx.fqb.select;

import com.lynx.fqb.api.select.Groupings;
import com.lynx.fqb.api.select.Havings;
import com.lynx.fqb.api.select.Orders;
import com.lynx.fqb.api.select.Restrictions;
import com.lynx.fqb.api.select.Results;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class Source<R, F> extends Clause<R, F>
        implements Restrictions<R, F>, Groupings, Havings, Orders<R, F>, Results<R> {

    public Source(CriteriaBuilder cb, CriteriaQuery<R> cq, Root<F> root) {
        super(cb, cq, root);
    }

}
