package com.lynx.fqb;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;

import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.User;
import com.lynx.fqb.transaction.TransactionalExecutor;

public class IntegrationTestBase {

    public static final String ORDER_ONE_NUMBER = "1/2017";

    public static final Long ORDER_ONE_ID = 3l;

    public static final String ORDER_TWO_NUMBER = "2/2017";

    public static final Long ORDER_TWO_ID = 6l;

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
            TransactionalExecutor.using(em).run(() -> {
                User creator = em.merge(new User(null, "Creator"));
                User supervisor = em.merge(new User(null, "Supervisor"));

                SellOrder order = new SellOrder(null, ORDER_ONE_NUMBER, new ArrayList<>(), DateTimeUtil.of(1970, 1, 1), BigDecimal.ONE, creator, supervisor);

                order.addItem(new Item(null, "Item 1.1", null, BigDecimal.ONE, 1));
                order.addItem(new Item(null, "Item 1.2", null, BigDecimal.ONE, 1));
                em.persist(order);

                order = new SellOrder(null, ORDER_TWO_NUMBER, new ArrayList<>(), DateTimeUtil.of(1980, 1, 1), BigDecimal.TEN, creator, null);
                order.addItem(new Item(null, "Item 2.1", null, new BigDecimal("1.5"), 2));
                em.persist(order);
            });
        }
    }

    @After
    public void destroy() {
        em.close();
    }

    protected <T> Path<T> root(Class<T> cls) {
        return cb.createQuery(cls).from(cls);
    }

}
