package com.lynx.fqb.func;

import javax.persistence.EntityManager;

import com.lynx.fqb.func.api.Sources;

public class Select implements Sources {

    protected final EntityManager em;

    public Select(EntityManager em) {
        this.em = em;
    }

    public static Select using(EntityManager em) {
        return new Select(em);
    }

    public EntityManager getEm() {
        return em;
    }
}
