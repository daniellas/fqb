package com.lynx.fqb.select;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.predicate.Predicates.*;
import static com.lynx.fqb.predicate.Predicates.of;
import static com.lynx.fqb.selection.Selections.*;
import static com.lynx.fqb.selection.Selections.of;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Item_;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.path.Paths;
import com.lynx.fqb.predicate.Predicates;

public class RestrictedSelectITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntitiesRestricted() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .where(of(equal(get(SellOrder_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectSingleResult() {
        SingleResult<SellOrder> singleResult = Select
                .from(SellOrder.class)
                .where(of(equal(get(SellOrder_.id), 1l)))
                .getSingleResult(em);

        Assert.assertTrue(singleResult.isPresent());
        Assert.assertNotNull(singleResult.getResult());
    }

    @Test
    public void shouldSelectEmptySingleResult() {
        SingleResult<SellOrder> singleResult = Select
                .from(SellOrder.class)
                .where(of(equal(get(SellOrder_.id), -1l)))
                .getSingleResult(em);

        Assert.assertFalse(singleResult.isPresent());
    }

    @Test
    public void shouldSelectNonUniqueSingleResult() {
        SingleResult<SellOrder> singleResult = Select
                .from(SellOrder.class)
                .where(of(gt(get(SellOrder_.id), -1l)))
                .getSingleResult(em);

        Assert.assertTrue(singleResult.isNonUnique());
    }

    @Test
    public void shouldSelectEntitiesRestrictedByLike() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .where(of(contains(get(SellOrder_.number), "1")))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectEntitiesRestrictedByMultiplePredicates() {
        List<SellOrder> resultList = Select
                .from(SellOrder.class)
                .where(of(or(
                        equal(SellOrder_.id, 1l),
                        equal(SellOrder_.number, IntegrationTestBase.ORDER_ONE_NUMBER))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectEntitiesRestrictedByPredicatesOnNestedPath() {
        List<Item> resultList = Select
                .from(Item.class)
                .where(of(equal(get(Item_.sellOrder).andThen(get(SellOrder_.id)), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultRestricted() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(path(get(SellOrder_.id)), path(get(SellOrder_.number))))
                .where(of(equal(get(SellOrder_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultFromPathsRestricted() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(path(get(SellOrder_.id)), path(get(SellOrder_.number))))
                .where(of(equal(Paths.get(SellOrder_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultFromNestedPathsRestricted() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Item.class)
                .with(of(path(get(Item_.id)), path(get(Item_.sellOrder).andThen(Paths.get(SellOrder_.number)))))
                .where(of(equal(get(Item_.id), 2l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectTupleRestricted() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(of(path(get(SellOrder_.id)), path(get(SellOrder_.number))))
                .where(of(equal(Paths.get(SellOrder_.id), 1l)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectRestrictedByObject() {
        SellOrder parent = Select.from(SellOrder.class).where(Predicates.of(Predicates.equal(SellOrder_.id, 1l))).getSingleResult(em).getResult();

        List<Item> resultList = Select.from(Item.class).where(Predicates.of(Predicates.equal(Item_.sellOrder, parent))).getResultList(em);
        Assert.assertFalse(resultList.isEmpty());
    }

}
