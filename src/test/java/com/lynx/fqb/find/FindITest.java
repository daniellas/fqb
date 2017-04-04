package com.lynx.fqb.find;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.Find;
import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;

public class FindITest extends IntegrationTestBase {

    @Test
    public void shouldGetById() {
        Optional<Parent> entity = Find.entity(Parent.class).byId(1l).apply(em);

        Assert.assertEquals(new Long(1), entity.get().getId());
    }

    @Test
    public void shouldReturnEmpty() {
        Optional<Parent> entity = Find.entity(Parent.class).byId(-11l).apply(em);

        Assert.assertFalse(entity.isPresent());
    }

}
