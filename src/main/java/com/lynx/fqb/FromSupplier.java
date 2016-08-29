package com.lynx.fqb;

import com.lynx.fqb.select.From;

@FunctionalInterface
public interface FromSupplier<F> {
    From<F> getFrom();
}
