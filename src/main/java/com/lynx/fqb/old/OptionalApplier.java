package com.lynx.fqb.old;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class OptionalApplier {

    public static <T, U, R> void applyOptionalBiFunction(Optional<BiFunction<T, U, R>> applier, T t, U u,
            Consumer<R> consumer) {
        applier.ifPresent((a) -> {
            consumer.accept(applier.get().apply(t, u));
        });
    }

    public static <T, U, R> void applyOptionalBiFunctionStream(Stream<Optional<BiFunction<T, U, R>>> appliers, T t, U u,
            Consumer<List<R>> consumer) {
        consumer.accept(appliers.filter(a -> a.isPresent()).map(a -> a.get().apply(t, u)).collect(Collectors.toList()));
    }

}
