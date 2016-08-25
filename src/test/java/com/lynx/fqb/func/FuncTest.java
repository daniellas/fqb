package com.lynx.fqb.func;

import org.junit.Test;

import com.lynx.fqb.TestBase;
import com.lynx.fqb.entity.Parent;

public class FuncTest extends TestBase {

    @Test
    public void selectFromShouldReturnList() {
        Select.using(em).from(Parent.class).list(0, 1);
    }
}
