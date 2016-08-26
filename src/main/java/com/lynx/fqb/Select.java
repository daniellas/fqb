package com.lynx.fqb;

import javax.persistence.EntityManager;

import com.lynx.fqb.api.Sources;

public class Select implements Sources {

    protected final EntityManager em;

    public Select(EntityManager em) {
        this.em = em;
    }

    public static Select using(EntityManager em) {
        return new Select(em);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
