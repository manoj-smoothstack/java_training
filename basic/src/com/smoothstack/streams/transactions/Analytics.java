package com.smoothstack.streams.transactions;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.*;

/**
 * 1 Find all transactions in the year 2011 and sort them by value (small to high).
 * 2 What are all the unique cities where the traders work?
 * 3 Find all traders from Cambridge and sort them by name.
 * 4 Return a string of all traders’ names sorted alphabetically.
 * 5 Are any traders based in Milan?
 * 6 Print all transactions’ values from the traders living in Cambridge.
 * 7. What’s the highest value of all the transactions?
 * 8. Find the transaction with the smallest value.
 */
public class Analytics {
    public static void main(String[] args) {
        new Analytics();
    }

    public Analytics() {
        problem1();
        problem2();
        problem3();
        problem4();
        problem5();
        problem6();
        problem7();
        problem8();
    }

    /**
     *  * 1 Find all transactions in the year 2011 and sort them by value (small to high).
     */
    public void problem1() {
        System.out.println("1 Find all transactions in the year 2011 and sort them by value (small to high).");
        List<Transaction> tr2011 =
                Data.transactions.stream()
                        .filter(transaction -> transaction.getYear() == 2011)
                        .sorted(comparing(Transaction::getValue))
                        .collect(toList());
        System.out.println(tr2011);
        /**
         * [{Trader:Brian in Cambridge, year: 2011, value:300}, {Trader:Raoul in Cambridge, year: 2011, value:400}]
         */

    }

    /**
     *  * 2 What are all the unique cities where the traders work?
     */
    public void problem2() {
        System.out.println("2 What are all the unique cities where the traders work?");
        List<String> cities =
                Data.transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .distinct()
                        .collect(toList());
        System.out.println(cities);
        /**
         * [Cambridge, Milan]
         */

        // you could also drop distinct() and use toSet() instead,
        // which would convert the stream into a set.
        Set<String> cities1 =
                Data.transactions.stream()
                        .map(transaction -> transaction.getTrader().getCity())
                        .collect(toSet());
        System.out.println(cities1);
        /**
         * [Milan, Cambridge]
         */
    }

    /**
     *  * 3 Find all traders from Cambridge and sort them by name.
     */
    public void problem3() {
        System.out.println("3 Find all traders from Cambridge and sort them by name.");
        List<Trader> traders =
                Data.transactions.stream()
                        .map(Transaction::getTrader)
                        .filter(trader -> trader.getCity().equals("Cambridge"))
                        .distinct()
                        .sorted(comparing(Trader::getName))
                        .collect(toList());
        System.out.println(traders);
        /**
         * [Trader:Alan in Cambridge, Trader:Brian in Cambridge, Trader:Raoul in Cambridge]
         */
    }

    /**
     *  * 4 Return a string of all traders’ names sorted alphabetically.
     */
    public void problem4() {
        System.out.println("4 Return a string of all traders’ names sorted alphabetically.");
        String traderStr =
                Data.transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr);
        /**
         * AlanBrianMarioRaoul
         */

        /**
         * Note that this solution isn’t very efficient (all Strings are repeatedly concatenated,
         * which creates a new String object at each iteration). In future, you’ll see a
         * more efficient solution that uses joining() as follows (which internally makes use of
         * a StringBuilder):
         */

        String traderStr1 =
                Data.transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .collect(joining());
        System.out.println(traderStr1);
        /**
         * AlanBrianMarioRaoul
         */
    }

    /**
     *  * 5 Are any traders based in Milan?
     */
    public void problem5() {
        System.out.println("5 Are any traders based in Milan?");
        boolean milanBased =
                Data.transactions.stream()
                        .anyMatch(transaction -> transaction.getTrader()
                                .getCity()
                                .equals("Milan"));
        System.out.println(milanBased);
        /**
         * true
         */

    }

    /**
     *  * 6 Print all transactions’ values from the traders living in Cambridge.
     */
    public void problem6() {
        System.out.println("6 Print all transactions’ values from the traders living in Cambridge.");
        Data.transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        /**
         * 300
         * 1000
         * 400
         * 950
         */
    }

    /**
     *  * 7. What’s the highest value of all the transactions?
     */
    public void problem7() {
        System.out.println("7. What’s the highest value of all the transactions?");
        Optional<Integer> highestValue =
                Data.transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);
        System.out.println(highestValue);
        /**
         * Optional[1000]
         */
    }

    /**
     *  * 8. Find the transaction with the smallest value.
     */
    public void problem8() {
        System.out.println("8. Find the transaction with the smallest value.");
        Optional<Transaction> smallestTransaction =
                Data.transactions.stream()
                        .reduce((t1, t2) ->
                                t1.getValue() < t2.getValue() ? t1 : t2);
        System.out.println(smallestTransaction);
        /**
         * Optional[{Trader:Brian in Cambridge, year: 2011, value:300}]
         */

        // A stream supports the methods min and max that take a Comparator as argument to
        // specify which key to compare with when calculating the minimum or maximum:

        Optional<Transaction> smallestTransaction1 =
                Data.transactions.stream()
                        .min(comparing(Transaction::getValue));
        System.out.println(smallestTransaction1);
        /**
         * Optional[{Trader:Brian in Cambridge, year: 2011, value:300}]
         */

    }
}
