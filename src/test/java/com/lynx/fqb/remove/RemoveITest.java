package com.lynx.fqb.remove;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Persist;
import com.lynx.fqb.Remove;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.transaction.TransactionalExecutor;

public class RemoveITest extends IntegrationTestBase {

    @Test
    public void shouldPersistRemove() {
        SellOrder entity = TransactionalExecutor.using(em).get(() -> {
            return Persist.entity(new SellOrder()).andThen(Optional::get).apply(em);
        });

        int size = Select.from(SellOrder.class).getResultList(em).size();

        TransactionalExecutor.using(em).run(() -> {
            Remove.entity(entity).apply(em);
        });

        Assert.assertNotEquals(size, Select.from(SellOrder.class).getResultList(em).size());
    }
}
