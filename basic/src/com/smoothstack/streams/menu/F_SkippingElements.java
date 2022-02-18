package com.smoothstack.streams.menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
Streams support the skip(n) method to return a stream that discards the first n elements.
If the stream has fewer elements than n, then an empty stream is returned. Note that
limit(n) and skip(n) are complementary! For example, the following code skips the first two
dishes that have more than 300 calories and returns the rest.
 */
public class F_SkippingElements {
    public static void main(String[] args) {
        new F_SkippingElements();
    }
    public F_SkippingElements() {
        skippingElemments();
    }
    public void skippingElemments() {
        System.out.println("\b****skippingElemments");
        List<Dish> dishes = Dishes.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        System.out.println(dishes);
        /**
         * [chicken, french fries, rice, pizza, salmon]
         */
    }
}
