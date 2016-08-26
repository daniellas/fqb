package com.lynx.fqb.old.api;

import javax.persistence.criteria.Root;

public interface RootHolder<F> {
    Root<F> getRoot();
}
