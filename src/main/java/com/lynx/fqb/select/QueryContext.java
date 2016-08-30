package com.lynx.fqb.select;

import java.util.Optional;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;

import com.lynx.fqb.EntityManagerSupplier;

public interface QueryContext extends EntityManagerSupplier {
    <T> Optional<CriteriaQuery<T>> doApply(Class<T> fromCls);

    <T> Class<T> fromCls();

    <T> Path<T> root();
}
