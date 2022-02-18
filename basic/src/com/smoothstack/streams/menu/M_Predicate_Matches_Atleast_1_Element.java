package com.smoothstack.streams.menu;

/**
 * The anyMatch method can be used to answer the question “Is there an element in the stream matching the given predicate?” For example, you can use it to find out whether the menu has a vegetarian option:
 The anyMatch method returns a boolean and is therefore a terminal operation.
 */
public class M_Predicate_Matches_Atleast_1_Element {
    public static void main(String[] args) {
        new M_Predicate_Matches_Atleast_1_Element();
    }
    public M_Predicate_Matches_Atleast_1_Element() {
        if (Dishes.menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
    }
}
