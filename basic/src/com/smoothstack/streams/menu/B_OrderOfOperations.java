package com.smoothstack.streams.menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
Intermediate operations such as filter or sorted return another stream as the return type.
This allows the operations to be connected to form a query. What’s important is that
intermediate operations don’t perform any processing until a termi- nal operation is invoked
on the stream pipeline—they’re lazy. This is because interme- diate operations can usually
be merged and processed into a single pass by the terminal operation.
To understand what’s happening in the stream pipeline, modify the code so each lambda also
prints the current dish it’s processing (like many demonstration and debugging techniques,
this is appalling programming style for production code but directly explains the order of
evaluation when you’re learning).

This code when executed will print the following:

filtering pork
mapping pork
filtering beef
mapping beef
filtering chicken
mapping chicken
[pork, beef, chicken]

You can notice several optimizations due to the lazy nature of streams. First, despite the
fact that many dishes have more than 300 calories, only the first three are selected! This is
because of the limit operation and a technique called short-circuiting, as we’ll explain in the
next. Second, despite the fact that filter and map are two separate oper- ations, they
were merged into the same pass (we call this technique loop fusion).
 */
public class B_OrderOfOperations {

    public static void main(String[] args) {
        new B_OrderOfOperations();
    }

    public B_OrderOfOperations() {
        orderOfOperations();
    }

    public void orderOfOperations() {
        System.out.println("\b****orderOfOperations");
        List<String> names =
                Dishes.menu.stream() // Get a stream from menu (the list of dishes).
                        .filter(d -> {
                            System.out.println("filtering " + d.getName());
                            return d.getCalories() > 300;
                        })
                        .map(d -> {
                            System.out.println("mapping " + d.getName());
                            return d.getName();
                        }) // get the names of the dishes
                        .limit(3) // Select only the first three.
                        .collect(toList()); // store the results in another list
        System.out.println(names);

        /**
         * ****orderOfOperations
         * filtering pork
         * mapping pork
         * filtering beef
         * mapping beef
         * filtering chicken
         * mapping chicken
         */
    }
}
