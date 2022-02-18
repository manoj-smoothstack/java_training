package com.smoothstack.streams.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Some streams have an encounter order that specifies the order in which items logically appear
 * in the stream (for example, a stream generated from a List or from a sorted sequence of data).
 * For such streams you may wish to find the first element. There’s the findFirst method for this,
 * which works similarly to findAny. For example, the code that follows, given a list of numbers,
 * finds the first square that’s divisible by 3:
 */
public class P_Finding_First_Element {
    public static void main(String[] args) {
        new P_Finding_First_Element();
    }

    /**
     * When to use findFirst and findAny
     * You may wonder why we have both findFirst and findAny. The answer is parallelism. Finding
     * the first element is more constraining in parallel. If you don’t care about which element
     * is returned, use findAny because it’s less constraining when using parallel streams.
     */
    public P_Finding_First_Element() {

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree =
                someNumbers.stream()
                        .map(x -> x * x)
                        .filter(x -> x % 3 == 0)
                        .findFirst(); // 9
        System.out.println(firstSquareDivisibleByThree);
    }
}
