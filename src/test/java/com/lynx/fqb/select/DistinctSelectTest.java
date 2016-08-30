package com.lynx.fqb.select;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.lynx.fqb.MockTestBase;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.paging.PageRequest;

public class DistinctSelectTest extends MockTestBase {

    @Before
    public void init() {
        super.init();
        Mockito.when(parentCriteriaQuery.distinct(Mockito.anyBoolean())).thenReturn(parentCriteriaQuery);
    }

    @Test
    public void shouldApplyDistinct() {
        Select.using(em).distinct().from(Parent.class).list();
        Mockito.verify(parentCriteriaQuery).distinct(true);
    }

    @Test
    public void shouldApplyDistinctPaged() {
        Select.using(em).distinct().from(Parent.class).list(PageRequest.of(0, 1));
        Mockito.verify(parentCriteriaQuery).distinct(true);
    }

}
