package com.lynx.fqb.api;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EntityManagerSupplier {
    EntityManager getEntityManager();
}
