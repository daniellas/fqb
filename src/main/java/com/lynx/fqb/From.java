package com.lynx.fqb;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;

import com.lynx.fqb.api.CriteriaBuilderOperations;
import com.lynx.fqb.api.Pageable;
import com.lynx.fqb.api.Results;

public class From<F> extends Select implements CriteriaBuilderOperations, Results<F> {

    protected final Supplier<Class<F>> fromCls;

    public From(EntityManager em, Class<F> fromCls) {
        super(em);
        this.fromCls = () -> fromCls;
    }

    @Override
    public List<F> apply(Pageable page) {
        return Optional.ofNullable(em)
                .map(m -> m.getCriteriaBuilder().createQuery(fromCls.get()))
                .map(q -> applyFrom(q, fromCls.get()))
                .map(q -> createTypedQuery(em, q))
                .map(tq -> applyPaging(tq, page))
                .map(tq -> tq.getResultList())
                .orElseThrow(() -> new IllegalStateException("EntitManager must be provided"));
    }

}
