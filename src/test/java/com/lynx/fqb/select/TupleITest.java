package com.lynx.fqb.select;

import java.util.List;

import javax.persistence.Tuple;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.SellOrder_;
import com.lynx.fqb.selection.Selections;

public class TupleITest extends IntegrationTestBase {

    @Test
    public void shouldAccessTupleResultByIndex() {
        List<Tuple> resultList = Select.tupleFrom(SellOrder.class)
                .with(Selections.of(
                        Selections.attr(SellOrder_.id),
                        Selections.attr(SellOrder_.number)))
                .getResultList(em);

        Assert.assertNotNull(resultList.get(0).get(0, Long.class));
        Assert.assertNotNull(resultList.get(0).get(1, String.class));
    }

}
