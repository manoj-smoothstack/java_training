package com.smoothstack.streams.menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
Streams support the method map, which takes a function as argument. The function is applied
to each element, mapping it into a new element (the word mapping is used because it has a
meaning similar to transforming but with the nuance of “creating a new version of” rather
than “modifying”). For example, in the following code you pass a method reference Dish::getName
to the map method to extract the names of the dishes in the stream.

Because the method getName returns a String, the stream outputted by the map method is of
type Stream<String>.
 */
public class G_ApplyAFunctionUsingMap {
    public static void main(String[] args) {
        new G_ApplyAFunctionUsingMap();
    }
    public G_ApplyAFunctionUsingMap() {
        applyAFunctionUsingMap();
    }
    public void applyAFunctionUsingMap() {
        System.out.println("\b****applyAFunctionUsingMap");
        List<String> dishNames = Dishes.menu.stream()
                .map(Dish::getName)
                .collect(toList());
        System.out.println(dishNames);
        /**
         * [pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon]
         */
    }
}
