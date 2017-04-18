package com.lynx.fqb.merge;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.Merge;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.transaction.TransactionalExecutor;

public class MergeITest extends IntegrationTestBase {

    @Test
    public void shouldMerge() {
        Parent entity = TransactionalExecutor.using(em).get(() -> {
            return Merge.entity(new Parent(null, "Tom", null, null)).apply(em);
        });

        Assert.assertEquals("Tom", entity.getName());
    }

}
