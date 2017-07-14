package com.lynx.fqb.select;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.persistence.NonUniqueResultException;

import lombok.RequiredArgsConstructor;

/**
 * Represents single result
 * 
 * @author dalas0
 *
 * @param <T>
 *            type of result
 */
public abstract class SingleResult<T> {

    /**
     * Construct result of successful query
     * 
     * @param result
     *            value
     * @return wrapped result
     * @param <T>
     *            result type
     */
    public static <T> SingleResult<T> ofResult(T result) {
        return new Result<>(result);
    }

    /**
     * Construct result of failed query
     * 
     * @param exception
     *            thrown during query execution
     * @return wrapped exception
     */
    public static <T> SingleResult<T> ofError(Exception exception) {
        Objects.requireNonNull(exception);
        return new Error<>(exception);
    }

    /**
     * Get wrapped result
     * 
     * @return wrapped result value or null
     */
    public abstract T getResult();

    /**
     * Get wrapped exception
     * 
     * @return wrapped exception or null
     */
    public abstract Exception getException();

    /**
     * Check if result is present
     * 
     * @return if result is present
     */
    public abstract boolean isPresent();

    /**
     * Check if exception is present
     * 
     * @return if exception is present
     */
    public abstract boolean isError();

    /**
     * Check if exception was caused by non unique result
     * 
     * @return if exception was caused due to {@link NonUniqueResultException}
     */
    public abstract boolean isNonUnique();

    /**
     * Apply consumer action on success
     * 
     * @param consumer
     *            with action to apply
     * @param <T>
     *            result type
     */
    public abstract void ifPresent(Consumer<? super T> consumer);

    /**
     * Map result using given mapper {@link Function}
     * 
     * @param mapper
     *            to apply
     * @return transformed value
     * @param <R>
     *            input type
     * @param output
     *            type
     */
    public abstract <R> R map(Function<? super T, R> mapper);

    /**
     * Return given other value in case of error
     * 
     * @param other
     *            value
     * @return other value
     * @param <T>
     *            other type
     */
    public abstract T orElse(T other);

    /**
     * Return given other value from {@link Supplier} in case of error
     * 
     * @param other
     *            value
     * @return other value
     * @param <T>
     *            other type
     */
    public abstract T orElseGet(Supplier<T> other);

    /**
     * Successful query result
     * 
     * @author dalas0
     *
     * @param <T>
     *            result type
     */
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
