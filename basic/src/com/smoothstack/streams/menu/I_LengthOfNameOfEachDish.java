package com.smoothstack.streams.menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
Let’s now return to the example where you extracted the name of each dish. What if you wanted
to find out the length of the name of each dish? You could do this by chaining another map
as follows:
 */
public class I_LengthOfNameOfEachDish {
    public static void main(String[] args) {
        new I_LengthOfNameOfEachDish();
    }
    public I_LengthOfNameOfEachDish() {
        lengthOfNameOfEachDish();
    }
    public void lengthOfNameOfEachDish() {
        System.out.println("\b****lengthOfNameOfEachDish");
        List<Integer> dishNameLengths = Dishes.menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(dishNameLengths);
        /**
         * [4, 4, 7, 12, 4, 12, 5, 6, 6]
         */
    }
}
