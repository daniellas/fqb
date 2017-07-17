package com.lynx.fqb.select;

import static org.junit.Assert.*;

import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.NonUniqueResultException;

import org.junit.Test;

import com.lynx.fqb.result.SingleResult;

public class SingleResultTest {

    @Test
    public void shouldBePresent() {
        assertTrue(SingleResult.ofResult(1l).isPresent());
    }

    @Test
    public void shouldBeError() {
        assertTrue(SingleResult.ofError(new RuntimeException()).isError());
    }

    @Test
    public void shouldNotBeError() {
        assertFalse(SingleResult.ofResult(1l).isError());
    }

    @Test
    public void shouldHaveResult() {
        assertEquals("1", SingleResult.ofResult("1").getResult());
    }

    @Test
    public void shouldHaveException() {
        RuntimeException e = new RuntimeException();

        assertEquals(e, SingleResult.ofError(e).getException());
    }

    @Test
    public void shouldNotHaveExceptionOnResult() {
        assertNull(SingleResult.ofResult(1l).getException());
    }

    @Test
    public void shouldBeNonUnique() {
        assertTrue(SingleResult.ofError(new NonUniqueResultException()).isNonUnique());
    }

    @Test
    public void shouldNotBeNonUniqueOnException() {
        assertFalse(SingleResult.ofError(new RuntimeException()).isNonUnique());
    }

    @Test
    public void shouldNotBeNonUniqueOnResult() {
        assertFalse(SingleResult.ofResult(1).isNonUnique());
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailOnNullException() {
        SingleResult.ofError(null);
    }

    @Test
    public void shouldConsumeOnResult() {
        AtomicInteger cnt = new AtomicInteger(0);

        SingleResult.ofResult(1l).ifPresent(r -> {
            cnt.incrementAndGet();
        });

        assertEquals(1, cnt.get());
    }

    @Test
    public void shouldHasResult() {
        assertTrue(SingleResult.ofResult(1l).isPresent());
    }

    @Test
    public void shouldMapResult() {
        assertEquals(2l, SingleResult.ofResult(1l).map(v -> v + 1).longValue());
    }

    @Test
    public void shouldReturnAlternativeValue() {
        assertEquals(2l, SingleResult.ofResult(null).orElse(2l));
    }

    @Test
    public void shouldReturnResultValue() {
        assertEquals(1l, SingleResult.ofResult(1l).orElse(2l).longValue());
    }

    @Test
    public void shouldReturnAlternativeValueBySupplier() {
        assertEquals(2l, SingleResult.ofResult(null).orElseGet(() -> 2l));
    }

    @Test
    public void shouldReturnResultValueBySupplier() {
        assertEquals(1l, SingleResult.ofResult(1l).orElseGet(() -> 2l).longValue());
    }

    @Test
    public void shouldHaveNullResultOnError() {
        assertNull(SingleResult.ofError(new NonUniqueResultException()).getResult());
    }

    @Test
    public void shouldNotBePresentOnError() {
        assertFalse(SingleResult.ofError(new NonUniqueResultException()).isPresent());
    }

    @Test
    public void shouldNotConsumeOnError() {
        AtomicInteger cnt = new AtomicInteger(0);

        SingleResult.ofError(new NonUniqueResultException()).ifPresent(r -> {
            cnt.incrementAndGet();
        });

        assertEquals(0, cnt.get());
    }

    @Test
    public void shouldMapOnError() {
        assertEquals(1l, SingleResult.ofError(new NonUniqueResultException()).map(v -> 1l).longValue());
    }

    @Test
    public void shouldReturnAlternateValueOnError() {
        assertEquals(1l, SingleResult.ofError(new NonUniqueResultException()).orElse(1l));
    }

    @Test
    public void shouldReturnAlternateValueBySupplierOnError() {
        assertEquals(1l, SingleResult.ofError(new NonUniqueResultException()).orElseGet(() -> 1l));
    }

}
