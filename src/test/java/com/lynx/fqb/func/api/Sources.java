package com.lynx.fqb.func.api;

import javax.persistence.EntityManager;

import com.lynx.fqb.func.SelectFrom;

public interface Sources {

   EntityManager getEm();
    
    default <FROM> SelectFrom<FROM> from(Class<FROM> fromCls) {
        return new SelectFrom<>(getEm(), fromCls);
    }
}
