package com.lynx.fqb;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Parent;

public class IntegrationTestBase {

    protected static EntityManagerFactory emf;

    protected EntityManager em;

    protected CriteriaBuilder cb;

    private static boolean initialized;

    @BeforeClass
    public static void initOnce() {
        emf = Persistence.createEntityManagerFactory("test");
    }

    @Before
    public void init() {
        em = emf.createEntityManager();
        cb = em.getCriteriaBuilder();

        if (!initialized) {
            initialized = true;

            EntityTransaction tx = em.getTransaction();

            tx.begin();
            Parent parent = new Parent(null, "Max", new ArrayList<>());

            parent.addChild(new Child());
            em.persist(parent);

            parent = new Parent(null, "John", new ArrayList<>());
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
