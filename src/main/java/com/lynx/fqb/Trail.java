package com.lynx.fqb;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Implements converted trail of consecutively created objects.
 * 
 * 
 * @author daniel.las
 *
 * @param <T>
 *            input type
 * @param <I>
 *            converted type
 */
public abstract class Trail<T, I> implements Supplier<List<I>> {

    private final List<I> items;

    /**
     * Default constructor taking existing items and new element to add
     * 
     * @param items
     *            list of existing items, if null new list containing converted
     *            element is created
     * @param element
     *            new history element, if null, no element will be added to
     *            items, new empty items list will be created
     */
    protected Trail(List<I> items, T element) {
        this.items = Optional.ofNullable(element)
                .map(e -> Optional.ofNullable(items)
                        .map(l -> {
                            l.add(converter().apply(e));

                            return l;
                        })
                        .orElse(new LinkedList<I>(Arrays.asList(converter().apply(e)))))
                .orElse(new LinkedList<>());

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
