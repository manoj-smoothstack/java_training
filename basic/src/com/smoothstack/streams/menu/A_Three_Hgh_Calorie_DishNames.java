package com.smoothstack.streams.menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 What exactly is a stream? A short definition is “a sequence of elements from a source that
 supports data processing operations.” Let’s break down this definition step by step:

 - Sequence of elements: Like a collection, a stream provides an interface to a sequenced set of
 values of a specific element type. Because collections are data structures, they’re mostly about
 storing and accessing elements with specific time/space complexities (for example, an ArrayList vs.
 a LinkedList). But streams are about expressing computations such as filter, sorted, and map that
 you saw earlier. Collections are about data; streams are about computations. We explain this
 idea in greater detail in the coming sections.

 - Source: Streams consume from a data-providing source such as collections, arrays, or I/O resources.
 Note that generating a stream from an ordered collec- tion preserves the ordering.

 The elements of a stream coming from a list will have the same order as the list.

 - Data processing operations: Streams support database-like operations and common operations
 from functional programming languages to manipulate data, such as filter, map, reduce, find,
 match, sort, and so on. Stream operations can be executed either sequentially or in parallel.

 In addition, stream operations have two important characteristics:
 - Pipelining—Many stream operations return a stream themselves, allowing opera- tions to be chained
 and form a larger pipeline. This enables certain optimizations that we explain in the next example,
 such as laziness and short-circuiting. A pipeline of operations can be viewed as a database-like
 query on the data source.

 - Internal iteration—In contrast to collections, which are iterated explicitly using an iterator,
 stream operations do the iteration behind the scenes for you. We briefly mentioned this idea
 and return to it later in the next section.

 By contrast, a stream is a conceptually fixed data structure (you can’t add or remove elements
 from it) whose elements are computed on demand. This gives rise to significant programming
 benefits.
 */
public class A_Three_Hgh_Calorie_DishNames {

    public static void main(String[] args) {
        new A_Three_Hgh_Calorie_DishNames();
    }

    public A_Three_Hgh_Calorie_DishNames() {
        threeHighCalorieDishNames();
    }

    /**
     * In this example, you first get a stream from the list of dishes by calling the stream
     * method on menu. The data source is the list of dishes (the menu) and it provides a
     * sequence of elements to the stream. Next, you apply a series of data processing
     * operations on the stream: filter, map, limit, and collect. All these operations
     * except collect return another stream so they can be connected to form a pipeline,
     * which can be viewed as a query on the source. Finally, the collect operation
     * starts processing the pipeline to return a result (it’s different because it
     * returns something other than a stream—here, a List). No result is produced,
     * and indeed no element from menu is even selected, until collect is invoked.
     *
     * You can think of it as if the method invoca- tions in the chain are queued up until collect is called. Figure 4.2 shows the sequence of stream operations: filter, map, limit, and collect, each of which is briefly described here:
     * - filter—Takes a lambda to exclude certain elements from the stream.
     * In this case, you select dishes that have more than 300 calories by passing
     * the lambda d -> d.getCalories() > 300.
     * - map—Takes a lambda to transform an element into another one or to extract
     * infor- mation. In this case, you extract the name for each dish by passing
     * the method ref- erence Dish::getName, which is equivalent to the lambda d -> d.getName().
     * - limit—Truncates a stream to contain no more than a given number of elements.
     * -collect—Converts a stream into another form. In this case you convert
     * the stream into a list. It looks like a bit of magic; we describe how collect
     * works in more detail. At the moment, you can see collect as an operation
     * that takes as an argument various recipes for accumulating the elements of a stream
     * into a summary result. Here, toList() describes a recipe for converting a stream
     * into a list.
     *
     * Notice how the code we just described is very different than what you’d write if
     * you were to process the list of menu items step by step. First, you use a much
     * more declar- ative style to process the data in the menu where you say what needs
     * to be done: “Find names of three high-calorie dishes.” You don’t implement the
     * filtering (filter), extracting (map), or truncating (limit) functionalities;
     * they’re available through the Streams library. As a result, the Streams API
     * has more flexibility to decide how to opti- mize this pipeline. For example,
     * the filtering, extracting, and truncating steps could be merged into a single
     * pass and stop as soon as three dishes are found. We show an example to demonstrate
     * that in the next.
     *
     * Let’s now stand back a little and examine the conceptual differences between the
     * Collections API and the new Streams API, before we explore in more detail what
     * operations you can perform with a stream.
     *
     * Both the existing Java notion of collections and the new notion of streams provide
     * interfaces to data structures representing a sequenced set of values of the element type.
     * By sequenced, we mean that we commonly step through the values in turn rather than
     * randomly accessing them in any order. So what’s the difference?
     * We’ll start with a visual metaphor. Consider a movie stored on a DVD. This is a
     * collection (perhaps of bytes or of frameswe don’t care which here) because it contains
     * the whole data structure. Now consider watching the same video when it’s being
     * streamed over the internet. This is now a stream (of bytes or frames). The streaming
     * video player needs to have downloaded only a few frames in advance of where the user
     * is watching, so you can start displaying values from the beginning of the stream
     * before most of the values in the stream have even been computed (consider streaming
     * a live football game). Note particularly that the video player may lack the memory
     * to buffer the whole stream in memory as a collection—and the startup time would be
     * appalling if you had to wait for the final frame to appear before you could start s
     * howing the video. You might choose for video-player implementation reasons to buffer
     * a part of a stream into a collection, but this is distinct from the conceptual difference.
     * In coarsest terms, the difference between collections and streams has to do with
     * when things are computed. A collection is an in-memory data structure that holds
     * all the values the data structure currently has—every element in the collection has
     * to be computed before it can be added to the collection. (You can add things to, and
     * remove them from, the collection, but at each moment in time, every element in the
     * collection is stored in memory; elements have to be computed before becoming part
     * of the collection.)
     *
     * stream containing all the prime numbers (2,3,5,7,11,...) even though there are an
     * infinite number of them. The idea is that a user will extract only the values they
     * require from a stream, and these elements are produced—invisibly to the user—only
     * as and when required. This is a form of a producer-consumer relationship. Another
     * view is that a stream is like a lazily constructed collection: values are computed
     * when they’re solicited by a consumer (in management speak this is demand-driven, or
     * even just-in-time, manufacturing).
     * In contrast, a collection is eagerly constructed (supplier-driven: fill your warehouse
     * before you start selling, like a Christmas novelty that has a limited life). Applying
     * this to the primes example, attempting to construct a collection of all prime numbers
     * would result in a program loop that forever computes a new prime, adding it to the
     * collection, but of course could never finish making the collection, so the consumer
     * would never get to see it.
     */
    public void threeHighCalorieDishNames() {
        System.out.println("\b****threeHighCalorieDishNames");
        List<String> threeHighCaloricDishNames =
                Dishes.menu.stream() // Get a stream from menu (the list of dishes).
                        .filter(d -> d.getCalories() > 300)
                        .map(Dish::getName) // get the names of the dishes
                        .limit(3) // Select only the first three.
                        .collect(toList()); // store the results in another list
        System.out.println(threeHighCaloricDishNames);
        /**
         * ****threeHighCalorieDishNames
         * [pork, beef, chicken]
         */
    }
}
