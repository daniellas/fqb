package com.lynx.fqb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Implements converted history of consecutively created objects.
 * 
 * 
 * @author daniel.las
 *
 * @param <T>
 *            input type
 * @param <I>
 *            converted type
 */
public abstract class History<T, I> implements Supplier<List<I>> {

    private final List<I> items;

    /**
     * Default constructor taking existing items and new element to add
     * 
     * @param items
     *            list of existing items, in case of null new list containing
     *            converted element is created
     * @param element
     *            new history element
     */
    protected History(List<I> items, T element) {
        this.items = Optional.ofNullable(items)
                .map(l -> {
                    l.add(converter().apply(element));

                    return l;
                })
                .orElse(new ArrayList<I>(Arrays.asList(converter().apply(element))));

    }

    /**
     * Converter function provider
     * 
     * @return converting function
     */
    protected abstract Function<T, I> converter();

    @Override
    public List<I> get() {
        return items;
    }
}
