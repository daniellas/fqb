package com.lynx.fqb;

import static org.hamcrest.Matchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import com.lynx.fqb.entity.Country;
import com.lynx.fqb.entity.Item;
import com.lynx.fqb.entity.Product;
import com.lynx.fqb.entity.SellOrder;
import com.lynx.fqb.entity.User;
import com.lynx.fqb.select.Result;
import com.lynx.fqb.transaction.TransactionalExecutor;

public class IntegrationTestBase {

    public static final String ORDER_ONE_NUMBER = "1/2017";

    public static final Long ORDER_ONE_ID = 5l;

    public static final String ORDER_TWO_NUMBER = "2/2017";

    public static final Long ORDER_TWO_ID = 8l;

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
                Country country = em.merge(new Country(null, "Poland"));
                User creator = em.merge(new User(null, "Creator", country));
                User supervisor = em.merge(new User(null, "Supervisor", country));
                Product product = em.merge(new Product(null, "Product one"));

                SellOrder order = new SellOrder(null, ORDER_ONE_NUMBER, new ArrayList<>(), DateTimeUtil.of(1970, 1, 1),
                        BigDecimal.ONE, creator, supervisor);

                order.addItem(new Item(null, "Item 1.1", null, BigDecimal.ONE, 1, product));
                order.addItem(new Item(null, "Item 1.2", null, BigDecimal.ONE, 1, product));
                em.persist(order);

                order = new SellOrder(null, ORDER_TWO_NUMBER, new ArrayList<>(), DateTimeUtil.of(1980, 1, 1),
                        BigDecimal.TEN, creator, null);
                order.addItem(new Item(null, "Item 2.1", null, new BigDecimal("1.5"), 2, product));
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

    protected <T> void assertOrder(List<T> resultList, Comparator<T> comparator, IntFunction<T[]> generator) {
        Assert.assertThat(resultList, contains(resultList.stream().sorted(comparator).toArray(generator)));
    }

    /**
     * Get result list from given query and assert not empty
     * 
     * @return result {@link Consumer}
     */
    protected Consumer<Result<?, ?>> assertListResultNotEmpty() {
        return result -> Assert.assertFalse(result.getResultList(em).isEmpty());
    }

    /**
     * Get result list from given query and assert empty
     * 
     * @return result {@link Consumer}
     */
    protected Consumer<Result<?, ?>> assertListResultEmpty() {
        return result -> Assert.assertTrue(result.getResultList(em).isEmpty());
    }

}
