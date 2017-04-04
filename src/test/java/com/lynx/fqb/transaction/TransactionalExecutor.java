package com.lynx.fqb.transaction;

import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(staticName = "using")
public class TransactionalExecutor {

    private final EntityManager em;

    public <E> E get(Supplier<E> supplier) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        E result = supplier.get();
        tx.commit();

        return result;
    }

    public <E> void run(Runnable runner) {
        EntityTransaction tx = em.getTransaction();

        tx.begin();
        runner.run();
        tx.commit();
    }
}
