package com.smoothstack.streams.menu;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
Let’s take a slightly different example to solidify your understanding of map. Given a
list of words, you’d like to return a list of the number of characters for each word. How
would you do it? You’d need to apply a function to each element of the list. This sounds
like a job for the map method! The function to apply should take a word and return its
length. You can solve this problem as follows by passing a method reference String::length to map:
 */
public class H_NumCharsOfEachWord {
    public static void main(String[] args) {
        new H_NumCharsOfEachWord();
    }
    public H_NumCharsOfEachWord() {
        numCharsOfEachWord();
    }
    public void numCharsOfEachWord() {
        System.out.println("\b****numCharsOfEachWord");
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        System.out.println(words);
        System.out.println(wordLengths);
        /**
         * [Java8, Lambdas, In, Action]
         * [5, 7, 2, 6]
         */
    }
}
