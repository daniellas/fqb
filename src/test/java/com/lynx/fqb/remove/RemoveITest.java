package com.lynx.fqb.remove;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Persist;
import com.lynx.fqb.Remove;
import com.lynx.fqb.Select;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.transaction.TransactionalExecutor;

public class RemoveITest extends IntegrationTestBase {

    @Test
    public void shouldPersistRemove() {
        Parent entity = TransactionalExecutor.using(em).get(() -> {
            return Persist.entity(new Parent()).andThen(Optional::get).apply(em);
        });

        int size = Select.from(Parent.class).getResultList(em).size();

        TransactionalExecutor.using(em).run(() -> {
            Remove.entity(entity).apply(em);
        });

        Assert.assertNotEquals(size, Select.from(Parent.class).getResultList(em).size());
    }
}
