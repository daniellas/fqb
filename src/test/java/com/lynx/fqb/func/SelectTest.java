package com.lynx.fqb.func;

import org.junit.Test;

import com.lynx.fqb.IntegrationTestBase;
import com.lynx.fqb.entity.Parent;

public class SelectTest extends IntegrationTestBase {

    @Test
    public void shouldSelectFrom() {
        Select.from(Parent.class).getResultList(em);
    }

    @Test
    public void shouldSelectFromWithSelection() {
        Select.entity(Parent.class).from(Parent.class).getResultList(em);
    }

    @Test
    public void shouldSelectFromWithPaging() {
        Select.from(Parent.class).getResultList(em, 0, 100);
    }

    @Test
    public void shouldSelectSingleResult() {
        Select.from(Parent.class).getResult(em);
    }

}
