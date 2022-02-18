package com.smoothstack.streams.menu;

import java.util.Optional;

/**
 * The findAny method returns an arbitrary element of the current stream. It can be used in conjunction
 * with other stream operations. For example, you may wish to find a dish that’s vegetarian. You
 * can combine the filter method and findAny to express this query
 * The stream pipeline will be optimized behind the scenes to perform a single pass and finish as
 * soon as a result is found by using short-circuiting. But wait a minute; what’s this Optional
 * thing in the code?
 *
 * The Optional<T> class (java.util.Optional) is a container class to represent the existence or
 * absence of a value. In the previous code, it’s possible that findAny doesn’t find any element.
 * Instead of returning null, which is well known for being error prone, the Java 8 library designers
 * introduced Optional<T>. We won’t go into the details of Optional here, because we show in detail
 * in  how your code can benefit from using Optional to avoid bugs related to null checking.
 * But for now, it’s good to know that there are a few methods available in Optional that force you
 * to explicitly check for the presence of a value or deal with the absence of a value:
 * - isPresent() returns true if Optional contains a value, false otherwise.
 * - ifPresent(Consumer<T> block) executes the given block if a value is present. We introduced the
 * Consumer functional interface it lets you pass a
 * lambda that takes an argument of type T and returns void.
 * - T get() returns the value if present; otherwise it throws a NoSuchElement-
 * Exception.
 * -T orElse(T other) returns the value if present; otherwise it returns a default value.
 *
 For example, in the previous code you’d need to explicitly check for the presence of a dish in
 the Optional object to access its name:

 menu.stream()
 .filter(Dish::isVegetarian)
 .findAny()
 .ifPresent(d -> System.out.println(d.getName());

 */
public class O_Finding_An_Element {
    public static void main(String[] args) {
        new O_Finding_An_Element();
    }

    public O_Finding_An_Element() {
        Optional<Dish> dish =
                Dishes.menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
        System.out.println(dish);
    }
}
