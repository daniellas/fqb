package com.lynx.fqb.func;

import javax.persistence.EntityManager;

public class Select<RESULT, FROM> implements Sources<RESULT, FROM>, Selections<RESULT, FROM> {

    public Select(EntityManager em) {

    }

    public static <RESULT, FROM> Select<RESULT, FROM> using(EntityManager em, Class<RESULT> resultCl, Class<FROM> fromCls) {
        return new Select<>(em);
    }

}
