package com.lynx.fqb;

import java.util.List;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

public class TrailTest {

    @Test
    public void shouldHasNoItemsOnNulls() {
        Assert.assertTrue(new TestedTrail(null, null).get().isEmpty());
    }

    @Test
    public void shouldHasSingleItem() {
        Assert.assertEquals(1, new TestedTrail(null, new Object()).get().size());
    }

    @Test
    public void shouldHasTwoItems() {
        TestedTrail t1 = new TestedTrail(null, new Object());
        TestedTrail t2 = new TestedTrail(t1.get(), new Object());

        Assert.assertEquals(2, t2.get().size());
    }

    private static class TestedTrail extends Trail<Object, Object> {

        public TestedTrail(List<Object> items, Object element) {
            super(items, element);
        }

        @Override
        protected Function<Object, Object> converter() {
            return o -> o;
        }

    }
}
