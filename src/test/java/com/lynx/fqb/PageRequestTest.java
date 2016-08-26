package com.lynx.fqb;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.api.Pageable;

public class PageRequestTest {

    @Test
    public void shouldCreateCorrectPageRequest() {
        Pageable pageRequest = PageRequest.of(0, 1);

        Assert.assertEquals(0, pageRequest.getPageNumber());
        Assert.assertEquals(0, pageRequest.getOffset());
        Assert.assertEquals(1, pageRequest.getPageSize());
    }

    @Test
    public void shouldHaveCorrectToString() {
        Assert.assertEquals("[0,1]", PageRequest.of(0, 1).toString());
    }

    @Test
    public void shouldReturnCorrectOffset() {
        Assert.assertEquals(0, PageRequest.of(0, 1).getOffset());
        Assert.assertEquals(0, PageRequest.of(0, 1).getOffset());
        Assert.assertEquals(10, PageRequest.of(1, 10).getOffset());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnOnIncorrectPageNumber() {
        PageRequest.of(-1, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnOnPageSizeEqualZero() {
        PageRequest.of(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailOnOnPageSizeBelowZero() {
        PageRequest.of(0, -1);
    }

}
