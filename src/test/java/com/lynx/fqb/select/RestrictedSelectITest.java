package com.lynx.fqb.select;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;
import static com.lynx.fqb.selection.Selections.*;

import java.util.List;

import javax.persistence.Tuple;

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
import com.lynx.fqb.predicate.Predicates;

public class RestrictedSelectITest extends IntegrationTestBase {

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
        List<Parent> resultList = Select
                .from(Parent.class)
                .where(of(or(
                        equal(Parent_.id, 1l),
                        equal(Parent_.name, IntegrationTestBase.PARENT_MAX_NAME))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectEntitiesRestrictedByPredicatesOnNestedPath() {
        List<Child> resultList = Select
                .from(Child.class)
                .where(of(equal(get(Child_.parent).andThen(get(Parent_.id)), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultRestricted() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Parent.class)
                .with(of(path(get(Parent_.id)), path(get(Parent_.name))))
                .where(of(equal(get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultFromPathsRestricted() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Parent.class)
                .with(of(path(get(Parent_.id)), path(get(Parent_.name))))
                .where(of(equal(Paths.get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultFromNestedPathsRestricted() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Child.class)
                .with(of(path(get(Child_.id)), path(get(Child_.parent).andThen(Paths.get(Parent_.name)))))
                .where(of(equal(get(Child_.id), 2l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectTupleRestricted() {
        List<Tuple> resultList = Select.tupleFrom(Parent.class)
                .with(of(path(get(Parent_.id)), path(get(Parent_.name))))
                .where(of(equal(Paths.get(Parent_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectRestrictedByObject() {
        Parent parent = Select.from(Parent.class).where(Predicates.of(Predicates.equal(Parent_.id, 1l))).getSingleResult(em).get();

        List<Child> resultList = Select.from(Child.class).where(Predicates.of(Predicates.equal(Child_.parent, parent))).getResultList(em);
        Assert.assertFalse(resultList.isEmpty());
    }

}
