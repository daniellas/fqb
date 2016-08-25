package com.lynx.fqb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Parent;

public class TestBase {

    protected static EntityManagerFactory emf;

    protected EntityManager em;

    private static boolean initialized;

    @BeforeClass
    public static void initOnce() {
        emf = Persistence.createEntityManagerFactory("test");
    }

    @Before
    public void init() {
        em = emf.createEntityManager();
        if (!initialized) {
            initialized = true;

            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Parent parent = new Parent();

            parent.addChild(new Child());
            em.persist(parent);

            parent = new Parent();
            parent.addChild(new Child());
            em.persist(parent);
            tx.commit();
        }
    }

    @After
    public void destroy() {
        em.close();
    }

}
