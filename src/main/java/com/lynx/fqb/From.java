package com.lynx.fqb;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

import com.lynx.fqb.paging.Pageable;

public class From<F> implements CriteriaBuilderOperations, Orders<F>, ListResults<F>, SingleResults<F> {

    protected final Supplier<Class<F>> fromCls;

    final Select select;

    Path<F> fromPath;

    public From(Select select, Class<F> fromCls) {
        this.select = select;
        this.fromCls = () -> fromCls;
    }

    public From(Select select, Supplier<Class<F>> fromClsSupplier) {
        this.select = select;
        this.fromCls = fromClsSupplier;
    }

    Optional<CriteriaQuery<F>> doApply() {
        return Optional.ofNullable(select.getEntityManager())
                .map(m -> m.getCriteriaBuilder().createQuery(fromCls.get()))
                .map(q -> {
                    fromPath = applyFrom(q, fromCls.get());

                    return q;
                });
    }

    @Override
    public List<F> apply(Pageable page) {
        return applyListResult(select.getEntityManager(), doApply(), page);
    }

    @Override
    public F get() {
        return applySingleResult(select.getEntityManager(), doApply());
    }

    @Override
    public From<F> getFrom() {
        return this;
    }

    @Override
    public EntityManager getEntityManager() {
        return select.getEntityManager();
    }

}
