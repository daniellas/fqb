package com.lynx.fqb.func;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.CustomResult;
import com.lynx.fqb.entity.Parent;

public class SelectITest extends IntegrationTestBase {

    @Test
    public void shouldSelectEntities() {
        List<Parent> resultList = Select.from(Parent.class).getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultsFromPaths() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Parent.class)
                .with(null)
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    @Test
    public void shouldSelectCustomResultsFromNestedPaths() {
        List<CustomResult> resultList = Select.as(CustomResult.class)
                .from(Child.class)
                .with(null)
                .getResultList(em);

        Assert.assertFalse(resultList.isEmpty());
    }

    public void shouldRunFullQuery() {
        Select.from(Parent.class).join().where().groupBy().having().orderBy().getResultList(em);
    }
}
