package com.lynx.fqb.func;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.lynx.fqb.entity.Parent;

public class FuncTest {

    private EntityManager em;

    @Test
    public void test() {
        new Select<Parent, Parent>(em).from(Parent.class).list(Parent.class);

        Select.using(em, Parent.class, Parent.class).from(Parent.class).list(Parent.class);

    }
}
