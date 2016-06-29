package com.lynx.fqb.api;

import javax.persistence.criteria.Root;

public interface RootHolder<F> {
    Root<F> getRoot();
}
