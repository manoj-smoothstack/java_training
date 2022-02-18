package com.smoothstack.streams.menu;

import java.util.Arrays;
import java.util.List;

/**
Streams also support a method called distinct that returns a stream with unique ele- ments (
according to the implementation of the hashCode and equals methods of the objects produced
by the stream). For example, the following code filters all even num- bers from a list and
makes sure that there are no duplicates.
 */
public class D_FilteringWithUniqueElements {
    public static void main(String[] args) {
        new D_FilteringWithUniqueElements();
    }

    public D_FilteringWithUniqueElements() {
        filteringWithUniqueElements();
    }

    public void filteringWithUniqueElements() {
        System.out.println("\b****filteringWithUniqueElements");
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
        //System.out.println(numbers);
        /**
         * 2
         * 4
         */
    }
}
