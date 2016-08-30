package com.lynx.fqb.select;

@FunctionalInterface
public interface FromSupplier<F> {
    From<F> getFrom();
}
