package com.smoothstack.streams.menu;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
Note that, similarly to iterators, a stream can be traversed only once. After that a stream is
said to be consumed. You can get a new stream from the initial data source to traverse it
again just like for an iterator (assuming it’s a repeatable source like a col- lection; if
it’s an I/O channel, you’re out of luck). For example, the following code would throw an
exception indicating the stream has been consumed

For readers who like philosophical viewpoints, you can see a stream as a set of val- ues spread
out in time. In contrast, a collection is a set of values spread out in space (here, computer
memory), which all exist at a single point in time—and which you access using an iterator to
access members inside a for-each loop.

Another key difference between collections and streams is how they manage the itera- tion over data.
 */
public class B1_Stream_Traversible_Only_Once {
    public static void main(String[] args) {
        new B1_Stream_Traversible_Only_Once();
    }

    public B1_Stream_Traversible_Only_Once() {
        streamTraversableOnlyOnce();
    }

    public void streamTraversableOnlyOnce() {
        System.out.println("\b****streamTraversableOnlyOnce");
        List<String> title = Arrays.asList("Java8", "With", "Progress");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        // s.forEach(System.out::println); // IllegalStateException
    }
}
