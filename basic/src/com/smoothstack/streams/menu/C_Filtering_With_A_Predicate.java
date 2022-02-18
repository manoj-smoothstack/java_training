package com.smoothstack.streams.menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
The Streams interface supports a filter method (which you should be familiar with by now).
This operation takes as argument a predicate (a function returning a boolean) and returns a
stream including all elements that match the predicate. For example, you can create a
vegetarian menu by filtering all vegetarian dishes as follows
 */
public class C_Filtering_With_A_Predicate {

    public static void main(String[] args) {
        new C_Filtering_With_A_Predicate();
    }

    public C_Filtering_With_A_Predicate() {
        filteringWithAPredicate();
    }

    public void filteringWithAPredicate() {
        System.out.println("\b****filteringWithAPredicate");
        List<Dish> vegetarianMenu = Dishes.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(toList());
        System.out.println(vegetarianMenu);
        /**
         * [french fries, rice, season fruit, pizza]
         */
    }
}
