package com.lynx.fqb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public abstract class EqualityHashTestBase<T> {

    @Parameter
    public EqualityPair<T> testedObject;

    @Test
    public void testEquality() {
        if (testedObject.isEquality()) {
            Assert.assertTrue(testedObject.getThisObj().equals(testedObject.getThatObj()));
        } else {
            Assert.assertFalse(testedObject.getThisObj().equals(testedObject.getThatObj()));
        }
    }

    @Test
    public void hashCodeShouldBeCalculated() {
        Assert.assertNotNull(testedObject.getThisObj().hashCode());
    }

}
