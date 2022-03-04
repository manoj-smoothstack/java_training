package com.smoothstack.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class LambdaExpressions {
    public static void main(String[] args) {
        new LambdaExpressions();
    }

    public LambdaExpressions() {
        anonymousClass();
        lambda1();
        lambda2();
        lambda3();
        lambda4();
        lambda5();
        lambda6();
        lambda7();
        lambda8();
    }

    public void anonymousClass() {
        Runnable r2 = new Runnable() {
            public void run() {
                System.out.println("Hello World 2");
            }
        };
        r2.run();
    }

    interface StringLength {
        int len(String str);
    }

    public void lambda1() {

        StringLength sl = (String s) -> s.length();
        System.out.println(sl.len("Hello"));
    }

    class Apple {
        int weight = 100;

        public int getWeight() {
            return weight;
        }
    }

    @FunctionalInterface
    interface Condition {
        boolean check(Apple a);
    }

    /**
     * If you explore the new Java API, you’ll notice that functional interfaces are
     * annotated with @FunctionalInterface,
     * where we explore functional interfaces in depth). This annotation is used to
     * indicate that the interface is intended to be a functional interface. The compiler
     * will return a meaning- ful error if you define an interface using the
     *
     * @FunctionalInterface annotation and it isn’t a functional interface.
     * For example, an error message could be “Multiple non- overriding abstract
     * methods found in interface Foo” to indicate that more than one abstract method
     * is available. Note that the @FunctionalInterface annotation isn’t mandatory,
     * but it’s good practice to use it when an interface is designed for that purpose.
     * You can think of it like the @Override notation to indicate that a method is overridden.
     */
    public void lambda2() {

        Apple a = new Apple();


        Condition condition = (Apple b) -> b.getWeight() > 150;
        System.out.println(condition.check(a));
    }

    @FunctionalInterface
    interface Adder<T> {
        void print(T a, T b);
    }

    public void lambda3() {

        Adder<Integer> adder = (Integer x, Integer y) -> {
            System.out.println("Result: ");
            System.out.println(x + y);
        };
        adder.print(4, 5);
    }

    interface Get {
        int num();
    }

    public void lambda4() {

        Get get = () -> 42;
        System.out.println(get.num());
    }

    private void process(Runnable r) {
        r.run();
    }

    public void lambda5() {
        Runnable r1 = () -> System.out.println("Hello World 1");
        process(r1); // using Runnable lambda
    }

    public void lambda6() {
        process(() -> System.out.println("Direct Lambda temporary"));
    }

    @FunctionalInterface
    interface AdderNum<T extends Integer> {
        T add(T a, T b);
    }

    public <T extends Integer> T add(T x, T y, AdderNum adder) {
        return (T) adder.add(x, y);
    }

    public void lambda7() {
        AdderNum adder = (x, y) -> {
            return (x + y);
        };
        System.out.println(add(4, 5, adder));
        Integer x1 = 4;
        Integer x2 = 5;
        System.out.println(add(x1, x2, adder));
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T s : list) {
            result.add(f.apply(s));
        }
        return result;
    }

    @FunctionalInterface
    interface Function<T, R> {
        R apply(T t);
    }

    public void lambda8() {
        /**
         * The lambda below
         * (String s) -> s.length()
         * is the implementation of the apply() method above.
         */
        List<Integer> l = map(
                Arrays.asList("lambdas", "in", "action"), (String s) -> s.length()
        );
        System.out.println(l);
    }

    public interface IntPredicate {
        boolean test(int t);
    }

    /**
     * Java8 performance considerations with non-primitives
     */
    public void lambda9() {
        // no boxing
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        evenNumbers.test(1000);

        // boxing
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 1;
        oddNumbers.test(1000);
    }

    /**
     * Capture local variables
     */
    public void lambda10() {
        int portNumber = 1337; // becomes final!
        Runnable r = () -> System.out.println(portNumber);
        //portNumber = 3422; // this breaks
    }
}
