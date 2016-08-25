package com.lynx.fqb.func;

import javax.persistence.EntityManager;

public class Select {

    protected final EntityManager em;

    public Select(EntityManager em) {
        this.em = em;
    }

    public static Select using(EntityManager em) {
        return new Select(em);
    }

    public <FROM> SelectFrom<FROM> from(Class<FROM> fromCls) {
        return new SelectFrom<>(em, fromCls);
    }
}
