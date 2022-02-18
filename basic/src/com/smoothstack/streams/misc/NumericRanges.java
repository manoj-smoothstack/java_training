package com.smoothstack.streams.misc;

import java.util.stream.IntStream;

/**
 * A common use case when dealing with numbers is working with ranges of numeric values.
 * For example, suppose you’d like to generate all numbers between 1 and 100.
 * Java 8 introduces two static methods available on IntStream and LongStream to help
 * generate such ranges: range and rangeClosed. Both methods take the starting value of the
 * range as the first parameter and the end value of the range as the second parameter.
 * But range is exclusive, whereas rangeClosed is inclusive. Let’s look at an example:
 */
public class NumericRanges {

    public static void main(String[] args) {
        new NumericRanges();
    }

    public NumericRanges() {
        numericRange();
    }

    /**
     * Here you use the rangeClosed method to generate a range of all numbers from 1 to 100.
     * It produces a stream so you can chain the filter method to select only even numbers.
     * At this stage no computation has been done. Finally, you call count on the resulting
     * stream. Because count is a terminal operation, it will process the stream and return
     * the result 50, which is the number of even numbers from 1 to 100, inclusive. Note
     * that by comparison, if you were using IntStream.range(1, 100) instead, the result
     * would be 49 even numbers because range is exclusive.
     */
    public void numericRange() {
        IntStream evenNumbers = IntStream.rangeClosed(1, 100) .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());
        // 50
    }
}
