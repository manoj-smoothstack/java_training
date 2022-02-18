package com.smoothstack.streams.menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
Streams support the limit(n) method, which returns another stream that’s no longer than a
given size. The requested size is passed as argument to limit. If the stream is ordered, the
first elements are returned up to a maximum of n. For example, you can cre- ate a List by
selecting the first three dishes that have more than 300 calories as follows.

This is a combination of filter and limit. You can see that only the first three elements
that match the predicate are selected and the result is immediately returned.
Note that limit also works on unordered streams (for example, if the source is a Set).
In this case you shouldn’t assume any order on the result produced by limit.
 */
public class E_TruncatingAStream {
    public static void main(String[] args) {
        new E_TruncatingAStream();
    }

    public E_TruncatingAStream() {
        truncatingAStream();
    }
    public void truncatingAStream() {
        System.out.println("\b****truncatingAStream");
        List<Dish> dishes = Dishes.menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(toList());
        System.out.println(dishes);
        /**
         * [pork, beef, chicken]
         */
    }
}
