package com.lynx.fqb.select;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

import com.lynx.fqb.CriteriaQueryApplier;
import com.lynx.fqb.paging.Pageable;

public class From<F> implements CriteriaQueryApplier, Orders<F>, ListResults<F>, SingleResults<F> {

    protected final Supplier<Class<F>> fromCls;

    private final Select select;

    private Path<F> root;

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
                    root = applyFrom(q, fromCls.get());

                    return q;
                });
    }

    @Override
    public List<F> apply(Pageable page, boolean distinct) {
        return applyListResult(select.getEntityManager(), doApply()
                .map(q -> applyDistinct(q, distinct)), page);
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

    public Path<F> getRoot() {
        return root;
    }

}
