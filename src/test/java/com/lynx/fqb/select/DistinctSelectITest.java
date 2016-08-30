package com.lynx.fqb.select;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.paging.PageRequest;

public class DistinctSelectITest extends IntegrationTestBase {

    @Test
    public void shouldApplyDistinct() {
        Select.using(em).distinct().from(Parent.class).list();
        Select.using(em).from(Parent.class).listDistinct();
    }

    @Test
    public void shouldApplyDistinctPaged() {
        Select.using(em).from(Parent.class).listDistinct(PageRequest.of(0, 1));
    }

}
