package com.lynx.fqb.select;

import org.junit.Test;

import com.lynx.fqb.MockTestBase;

public class SelectTest extends MockTestBase {

    @Test(expected = UnsupportedOperationException.class)
    public void shouldFailOnFromCls() {
        Select.using(em).fromCls();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldFailOnRoot() {
        Select.using(em).root();
    }

}
