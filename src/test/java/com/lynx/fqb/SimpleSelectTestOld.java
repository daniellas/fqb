package com.lynx.fqb;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.lynx.fqb.entity.Child;
import com.lynx.fqb.entity.Child_;
import com.lynx.fqb.entity.Parent;
import com.lynx.fqb.entity.Parent_;
import com.lynx.fqb.old.Sorts;
import com.lynx.fqb.old.select.SelectQuery;

@Ignore
@Deprecated
public class SimpleSelectTestOld {

    private static EntityManagerFactory emf;

    private EntityManager em;

    private CriteriaBuilder cb;

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

            Parent parent = new Parent();

            parent.addChild(new Child());

            em.persist(parent);
        }
    }

    @After
    public void clean() {
        em.close();
    }

    @Test
    public void criteriaBuilderTest() {
        CriteriaQuery<Child> query = cb.createQuery(Child.class);
        Root<Child> from = query.from(Child.class);

        // query.where(cb.equal(from.get(Parent_.id), 1));
        query.orderBy(cb.asc(from.get(Child_.parent).get(Parent_.id)));

        from.get(Child_.parent).get(Parent_.id);
        TypedQuery<Child> result = em.createQuery(query);

        result.getResultList();
    }

    @Test
    public void parentSelectShouldReturnResultList() {

        new SelectQuery<Parent, Parent>(cb)
                .select(Parent.class)
                .from(Parent.class)
                .orderBy(Sorts.by(Parent_.id))
                .getResultList(em);
    }

    @Test
    public void childSelectShouldReturnResultList() {
        new SelectQuery<Child, Child>(cb)
                .select(Child.class)
                .from(Child.class)
                .getResultList(em);
    }

}
