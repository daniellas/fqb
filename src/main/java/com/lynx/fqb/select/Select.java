package com.lynx.fqb.select;

import javax.persistence.EntityManager;

public class Select implements QueryContext, Modifiers, Sources {

    final EntityManager em;

    private Select(EntityManager em) {
        this.em = em;
    }

    public static Select using(EntityManager em) {
        return new Select(em);
    }

    @Override
    public Select getSelect() {
        return this;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

}
