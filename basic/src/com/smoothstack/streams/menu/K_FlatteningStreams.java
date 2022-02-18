package com.smoothstack.streams.menu;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * You saw how to return the length for each word in a list using the method map.
 *     Let’s extend this idea a bit further: how could you return a list of all the
 *     unique characters for a list of words? For example, given the list of words
 *     ["Hello", "World"] you’d like to return the list ["H", "e", "l", "o", "W", "r", "d"].
 *
 *     You might think that this is easy, that you can just map each word into a
 *     list of characters and then call distinct to filter duplicate characters. A
 *     first go could be like this.
 [J, a, v, a]
 [L, a, m, b, d, a, s]
 [M, a, k, i, n, g]
 [P, r, o, g, r, e, s, s]
 */
public class K_FlatteningStreams {
    public static void main(String[] args) {
        new K_FlatteningStreams();
    }

    public K_FlatteningStreams() {
        flatteningStreams();
    }

    /**
    The problem with this approach is that the lambda passed to the map method returns
    a String[] (an array of String) for each word. So the stream returned by the map
    method is actually of type Stream<String[]>. What you really want is Stream<String>
    to represent a stream of characters. Figure 5.5 illustrates the problem.

    Luckily there’s a solution to this problem using the method flatMap! Let’s see step by
    step how to solve it.
     */
    public void flatteningStreams() {
        System.out.println("\b****flatteningStreams");
        List<String> words = Arrays.asList("Java", "Lambdas", "Making", "Progress");
        List<String[]> result = words.stream()
                .map(word -> word.split(""))
                //.distinct()
                .collect(toList());
        for (String[] ele : result) {
            System.out.println(Arrays.asList(ele));
        }
        /**
         [J, a, v, a]
         [L, a, m, b, d, a, s]
         [M, a, k, i, n, g]
         [P, r, o, g, r, e, s, s]
         */
    }
}
