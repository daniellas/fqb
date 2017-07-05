package com.lynx.fqb.select;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.NonUniqueResultException;

import lombok.RequiredArgsConstructor;

public abstract class SingleResult<T> {

    public static <T> SingleResult<T> ofResult(T result) {
        return new Result<>(result);
    }

    public static <T> SingleResult<T> ofError(Exception exception) {
        Objects.requireNonNull(exception);
        return new Error<>(exception);
    }

    public abstract T getResult();

    public abstract Exception getException();

    public abstract boolean isPresent();

    public abstract boolean isError();

    public abstract boolean isNonUnique();

    public abstract void ifPresent(Consumer<? super T> consumer);

    public abstract <R> R map(Function<? super T, R> mapper);

    public abstract T orElse(T other);

    public abstract T orElseGet(Supplier<T> other);

    @RequiredArgsConstructor
    public static final class Result<T> extends SingleResult<T> {

        private final T result;

        @Override
        public T getResult() {
            return result;
        }

        @Override
        public Exception getException() {
            return null;
        }

        @Override
        public boolean isPresent() {
            return Optional.ofNullable(result).isPresent();
        }

        @Override
        public boolean isError() {
            return false;
        }

        @Override
        public boolean isNonUnique() {
            return false;
        }

        @Override
        public void ifPresent(Consumer<? super T> consumer) {
            Optional.ofNullable(result).ifPresent(consumer);
        }

        @Override
        public <R> R map(Function<? super T, R> mapper) {
            return mapper.apply(result);
        }

        @Override
        public T orElse(T other) {
            return result == null ? other : result;
        }

        @Override
        public T orElseGet(Supplier<T> other) {
            return result == null ? other.get() : result;
        }

    }

    @RequiredArgsConstructor
    public static final class Error<T> extends SingleResult<T> {

        private final Exception exception;

        @Override
        public T getResult() {
            return null;
        }

        @Override
        public Exception getException() {
            return exception;
        }

        @Override
        public boolean isPresent() {
            return false;
        }

        @Override
        public boolean isError() {
            return true;
        }

        @Override
        public boolean isNonUnique() {
            return NonUniqueResultException.class.isAssignableFrom(exception.getClass());
        }

        @Override
        public void ifPresent(Consumer<? super T> consumer) {

        }

        @Override
        public <R> R map(Function<? super T, R> mapper) {
            return mapper.apply(null);
        }

        @Override
        public T orElse(T other) {
            return other;
        }

        @Override
        public T orElseGet(Supplier<T> other) {
            return other.get();
        }

    }

}
