package com.lynx.fqb.get;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.Get;
import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;

public class GetITest extends IntegrationTestBase {

    @Test
    public void shouldGetById() {
        Optional<Parent> entity = Get.from(Parent.class).byId(1l).apply(em);

        Assert.assertEquals(new Long(1), entity.get().getId());
    }

    @Test
    public void shouldReturnEmpty() {
        Optional<Parent> entity = Get.from(Parent.class).byId(-11l).apply(em);

        Assert.assertFalse(entity.isPresent());
    }

}
