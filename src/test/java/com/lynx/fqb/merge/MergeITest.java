package com.lynx.fqb.merge;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Merge;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.transaction.TransactionalExecutor;

public class MergeITest extends IntegrationTestBase {

    @Test
    public void shouldMerge() {
        SellOrder entity = TransactionalExecutor.using(em).get(() -> {
            return Merge.entity(new SellOrder(null, "1", null, null, null, null, null)).andThen(Optional::get).apply(em);
        });

        Assert.assertEquals("1", entity.getNumber());
    }

}
