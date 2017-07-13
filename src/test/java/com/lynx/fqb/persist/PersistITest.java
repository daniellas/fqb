package com.lynx.fqb.persist;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Persist;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.transaction.TransactionalExecutor;

public class PersistITest extends IntegrationTestBase {

    @Test
    public void shouldPersist() {
        SellOrder entity = TransactionalExecutor.using(em).get(() -> {
            return Persist.entity(new SellOrder(
                    null,
                    "New number",
                    null,
                    new Date(),
                    null,
                    null,
                    null))
                    .andThen(Optional::get).apply(em);
        });

        Assert.assertNotNull(entity.getId());
    }
}
