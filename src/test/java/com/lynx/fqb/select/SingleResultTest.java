package com.lynx.fqb.select;

import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.NonUniqueResultException;

import org.junit.Assert;
import org.junit.Test;

public class SingleResultTest {

    @Test
    public void shouldBePresent() {
        Assert.assertTrue(SingleResult.ofResult(1l).isPresent());
    }

    @Test
    public void shouldBeError() {
        Assert.assertTrue(SingleResult.ofError(new RuntimeException()).isError());
    }

    @Test
    public void shouldNotBeError() {
        Assert.assertFalse(SingleResult.ofResult(1l).isError());
    }

    @Test
    public void shouldHaveResult() {
        Assert.assertEquals("1", SingleResult.ofResult("1").get());
    }

    @Test
    public void shouldHaveException() {
        RuntimeException e = new RuntimeException();

        Assert.assertEquals(e, SingleResult.ofError(e).getException());
    }

    @Test
    public void shouldBeNonUnique() {
        Assert.assertTrue(SingleResult.ofError(new NonUniqueResultException()).isNonUnique());
    }

    @Test
    public void shouldNotBeNonUniqueOnException() {
        Assert.assertFalse(SingleResult.ofError(new RuntimeException()).isNonUnique());
    }

    @Test
    public void shouldNotBeNonUniqueOnResult() {
        Assert.assertFalse(SingleResult.ofResult(1).isNonUnique());
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailOnNullException() {
        SingleResult.ofError(null);
    }

    @Test
    public void shouldConsume() {
        AtomicInteger cnt = new AtomicInteger(0);

        SingleResult.ofResult(1l).ifPresent(r -> {
            cnt.incrementAndGet();
        });

        Assert.assertEquals(1, cnt.get());
    }

    @Test
    public void shouldHasResult() {
        Assert.assertTrue(SingleResult.ofResult(1l).getResult().isPresent());
    }
}
