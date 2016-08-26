package com.lynx.fqb;

import javax.persistence.EntityManager;

public class Select implements Sources, EntityManagerSupplier {

    protected final EntityManager em;

    public Select(EntityManager em) {
        this.em = em;
    }

    public static Select using(EntityManager em) {
        return new Select(em);
    }

    @Override
    public Select get() {
        return this;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
