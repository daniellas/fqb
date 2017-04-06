package com.lynx.fqb.select;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;
import static com.lynx.fqb.predicate.Predicates.of;
import static com.lynx.fqb.select.Selections.*;
import static com.lynx.fqb.select.Selections.of;

import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.predicate.Predicates.Context;

public class RestrictionsITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntitiesRestricted() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(equal(get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectSingleResult() {
        SingleResult<Parent> singleResult = Select
                .from(Parent.class)
                .where(of(equal(get(Parent_.id), 1l)))
                .getSingleResult(em);

        Assert.assertTrue(singleResult.isPresent());
        Assert.assertNotNull(singleResult.get());
    }

    @Test
    public void shouldSelectEmptySingleResult() {
        SingleResult<Parent> singleResult = Select
                .from(Parent.class)
                .where(of(equal(get(Parent_.id), -1l)))
                .getSingleResult(em);

        Assert.assertFalse(singleResult.isPresent());
    }

    @Test
    public void shouldSelectNonUniqueSingleResult() {
        SingleResult<Parent> singleResult = Select
                .from(Parent.class)
                .where(of(gt(get(Parent_.id), -1l)))
                .getSingleResult(em);

        Assert.assertTrue(singleResult.isNonUnique());
    }

    @Test
    public void shouldSelectEntitiesRestrictedByLike() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(contains(get(Parent_.name), "a")))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectEntitiesRestrictedByMultiplePredicates() {
        BiFunction<CriteriaBuilder, Root<Parent>, Context<Parent>> predicates = equal(get(Parent_.id), 1l)
                .andThen(and(equal(get(Parent_.name), "Max")))
                .andThen(or(equal(get(Parent_.name), "John"), equal(get(Parent_.name), "Anna")));

        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(predicates))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectEntitiesRestrictedByCombinedPredicates() {
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(equal(get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultRestricted() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Parent.class)
                .with(of(path(get(Parent_.id)), path(get(Parent_.name))))
                .where(of(equal(get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultFromPathsRestricted() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Parent.class)
                .with(of(path(get(Parent_.id)), path(get(Parent_.name))))
                .where(of(equal(Paths.get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultFromNestedPathsRestricted() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Child.class)
                .with(of(path(get(Child_.id)), path(get(Child_.parent).andThen(Paths.get(Parent_.name)))))
                .where(of(equal(get(Child_.id), 2l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectTupleRestricted() {
        List<Tuple> resultList = Select.tuple()
                .from(Parent.class)
                .with(of(path(get(Parent_.id)), path(get(Parent_.name))))
                .where(of(equal(Paths.get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

}
