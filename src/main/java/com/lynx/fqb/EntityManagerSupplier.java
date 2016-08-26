package com.lynx.fqb;

import javax.persistence.EntityManager;

@FunctionalInterface
public interface EntityManagerSupplier {
    EntityManager getEntityManager();
}
