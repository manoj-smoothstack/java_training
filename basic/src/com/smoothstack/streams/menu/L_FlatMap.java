package com.smoothstack.streams.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
Luckily there’s a solution to this problem using the method flatMap! Let’s see step by step how to solve it.

First, you need a stream of characters instead of a stream of arrays. There’s a method called Arrays.stream()
that takes an array and produces a stream.

Use it in the previous pipeline to see what happens.
*/
public class L_FlatMap {
    public static void main(String[] args) {
        new L_FlatMap();
    }

    public L_FlatMap() {
        flatMap();
    }

    public void flatMap() {
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        List<Stream<String>> list = streamOfwords
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());
        for (Stream<String> sstring : list) {
            System.out.println(sstring);
        }

        /*

        [G, o, o, d, b, y, e]
        [W, o, r, l, d]

        */

        /**
        // The current solution still doesn’t work! This is because you now end up with a list
        // of streams (more precisely, Stream<Stream<String>>)! Indeed, you first convert
        // each word into an array of its individual letters and then make each array into a
        // separate stream

        // You can fix this problem by using flatMap as follows:
         */

        streamOfwords = Arrays.stream(arrayOfWords);
        List<String> uniqueCharacters = streamOfwords
                        .map(w -> w.split(""))
                        .flatMap(Arrays::stream)
                        .distinct() // the distinct worked
                        .collect(Collectors.toList());

        System.out.println(uniqueCharacters);

        /**
        // [G, o, d, b, y, e, W, r, l]

        // Using the flatMap method has the effect of mapping each array not with a stream but
        // with the contents of that stream. All the separate streams that were generated when
        // using map(Arrays::stream) get amalgamated—flattened into a single stream.
        //In a nutshell, the flatMap method lets you replace each value of a stream with another
        // stream and then concatenates all the generated streams into a single stream.
         */
    }
}
