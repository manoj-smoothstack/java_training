package com.smoothstack.streams.menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * So far, the terminal operations you’ve seen return a boolean (allMatch and so on), void (forEach),
 * or an Optional object (findAny and so on). You’ve also been using collect to combine all elements
 * in a stream into a List.
 * In this section, you’ll see how you can combine elements of a stream to express more complicated
 * queries such as “Calculate the sum of all calories in the menu,” or “What is the highest calorie
 * dish in the menu?” using the reduce operation. Such queries combine all the elements in the
 * stream repeatedly to produce a single value such as an Integer. These queries can be classified
 * as reduction operations (a stream is reduced to a value). In functional programming-language jargon,
 * this is referred to as a fold because you can view this operation as repeatedly folding a long
 * piece of paper (your stream) until it forms a small square, which is the result of the fold operation.
 */
public class Q_Reducing {
    public static void main(String[] args) {

        new Q_Reducing();
    }

    public Q_Reducing() {
        basicSum();
        streamSum();
        streamSumMethod();
        sumWithNoInitialValue();
        maxValue();
    }

    /**
     * Each element of numbers is combined iteratively with the addition operator to form a result. You reduce the list of numbers into one number by repeatedly using addition. There are two parameters in this code:
     * -The initial value of the sum variable, in this case 0
     * -The operation to combine all the elements of the list, in this case +
     */
    public void basicSum() {
        int sum = 0;
        Integer[] numbers = {3, 2, 5, 6};
        for (int x : numbers) {
            sum += x;
        }
        System.out.println(sum);
        // 16
    }

    /**
     * reduce takes two arguments:
     * - An initial value, here 0.
     * - A BinaryOperator<T> to combine two elements and produce a new value; here
     * you use the lambda(a,b)->a+b.
     *
     * Let’s take an in-depth look into how the reduce operation happens to sum a stream of numbers.
     * First, 0 is used as the first parameter of the lambda (a), and 4 is con- sumed from the
     * stream and used as the second parameter (b). 0 + 4 produces 4, and it becomes the new
     * accumulated value. Then the lambda is called again with the accumulated value and the next
     * element of the stream, 5, which produces the new accumulated value, 9. Moving forward, the
     * lambda is called again with the accumulated value and the next element, 3, which produces 12.
     * Finally, the lambda is called with 12 and the last element of the stream, 9, which produces
     * the final value, 21.
     */
    public void streamSum() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
        // 6
    }

    /**
     * You can make this code more concise by using a method reference. In Java 8 the Integer class
     * now comes with a static sum method to add two numbers, which is just what you want instead
     * of repeatedly writing out the same code as lambda:
     */
    public void streamSumMethod() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum);
        // 6
    }

    /**
     * There’s also an overloaded variant of reduce that doesn’t take an initial value, but it
     * returns an Optional object.
     *
     * Why does it return an Optional<Integer>? Consider the case when the stream con-
     * tains no elements. The reduce operation can’t return a sum because it doesn’t have
     *
     * an initial value. This is why the result is wrapped in an Optional object to indicate that
     * the sum may be absent.
     */
    public void sumWithNoInitialValue() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
        System.out.println(sum);
        // Optional[6]
    }

    /**
     * It turns out that reduction is all you need to compute maxima and minima as well! Let’s
     * see how you can apply what you just learned about reduce to calculate the maxi- mum or
     * minimum element in a stream. As you saw, reduce takes two parameters:
     * - An initial value
     * - A lambda to combine two stream elements and produce a new value
     *
     * The lambda is applied step by step to each element of the stream with the addition operator,
     * as shown here. So you need a lambda that, given two elements, returns the maximum of them. T
     * he reduce operation will use the new value with the next element of the stream to produce
     * a new maximum until the whole stream is consumed!
     *
     * You could have equally well used the lambda (x,y)->x<y?x:y instead of Integer::min, but the
     * latter is easier to read.
     *
     * Benefit of the reduce method and parallelism
     * The benefit of using reduce compared to the step-by-step iteration summation that you wrote
     * earlier is that the iteration is abstracted using internal iteration, which enables the
     * internal implementation to choose to perform the reduce operation in parallel. The
     * iterative summation example involves shared updates to a sum variable, which doesn’t
     * parallelize gracefully. If you add in the needed synchronization, you’ll likely discover
     * that thread contention robs you of all the performance that parallelism was supposed to
     * give you! Parallelizing this computation requires a different approach: partition the input,
     * sum the partitions, and combine the sums. But now the code is starting to look really different.
     * You’ll see what this looks like using the fork/join framework. But for now it’s
     * important to realize that the mutable accumulator pattern is a dead end for parallelization.
     * You need a new pattern, and this is what reduce provides you. You’ll also see in
     * that to sum all the ele- ments in parallel using streams, there’s almost no modification to
     * your code: stream() becomes parallelStream():
     * int sum = numbers.parallelStream().reduce(0, Integer::sum);
     * But there’s a price to pay to execute this code in parallel, as we explain later: the lambda
     * passed to reduce can’t change state (for example, instance variables), and the operation
     * needs to be associative so it can be executed in any order.
     */
    public void maxValue() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        System.out.println(max);
        // Optional[6]
    }
}
