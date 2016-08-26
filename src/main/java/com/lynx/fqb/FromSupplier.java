package com.lynx.fqb;

@FunctionalInterface
public interface FromSupplier<F> {
    From<F> getFrom();
}
