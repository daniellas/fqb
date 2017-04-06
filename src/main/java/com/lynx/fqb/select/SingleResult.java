package com.lynx.fqb.select;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import javax.persistence.NonUniqueResultException;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class SingleResult<T> {

    @Getter
    private final Optional<T> result;

    @Getter
    private final Exception exception;

    public static <T> SingleResult<T> ofResult(T result) {
        return new SingleResult<>(Optional.ofNullable(result), null);
    }

    public static <T> SingleResult<T> ofError(Exception e) {
        Objects.requireNonNull(e);
        return new SingleResult<>(Optional.empty(), e);
    }

    public boolean isPresent() {
        return result.isPresent();
    }

    public boolean isError() {
        return exception != null;
    }

    public boolean isNonUnique() {
        return isError() && NonUniqueResultException.class.isAssignableFrom(exception.getClass());
    }

    public void ifPresent(Consumer<? super T> consumer) {
        result.ifPresent(consumer);
    }

    public T get() {
        return result.get();
    }

}
