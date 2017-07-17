package com.lynx.fqb.selection;

import static com.lynx.fqb.path.Paths.*;
import static com.lynx.fqb.selection.Selections.*;

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
import com.lynx.fqb.selection.Selections;

public class SelectionsITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntities() {
        List<SellOrder> resultList = Select.from(SellOrder.class).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectPagedEntities() {
        List<SellOrder> resultList = Select.from(SellOrder.class).getResultList(em, 0, 1);

        Assert.assertEquals(1, resultList.size());
    }

    @Test
    public void shouldSelectDistinctEntities() {
        List<SellOrder> resultList = Select.distinct(SellOrder.class).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResults() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, SellOrder.class)
                .with(of(path(get(SellOrder_.total)), path(get(SellOrder_.number))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultsFromNestedPaths() {
        List<CustomResult> resultList = Select.customFrom(CustomResult.class, Item.class)
                .with(of(
                        path(get(Item_.price)),
                        path(get(Item_.sellOrder).andThen(get(SellOrder_.number)))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectAttrFromSuperType() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(Selections.of((cb, root) -> root.get(SellOrder_.dateCreate)))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectAlias() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(Selections.of(Selections.attr(SellOrder_.number).andThen(Selections.alias("nr"))))
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomWithVarArgsSelection() {
        assertListResultNotEmpty().accept(Select.tupleFrom(SellOrder.class)
                .with(
                        Selections.attr(SellOrder_.id),
                        Selections.attr(SellOrder_.number)));
    }

}
