package com.lynx.fqb;

import org.junit.Assert;
import org.junit.Test;

import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.func.Select;

public class FuncTest extends TestBase {

    @Test
    public void selectShouldReturnAll() {
        Assert.assertEquals(2, Select.using(em).from(Parent.class).list().size());
    }

    @Test
    public void selectFirstShouldReturnOne() {
        Assert.assertEquals(1, Select.using(em).from(Parent.class).list(0, 1).size());
    }

    @Test
    public void selectSecondShouldReturnOne() {
        Assert.assertEquals(1, Select.using(em).from(Parent.class).list(1, 1).size());
    }

}
