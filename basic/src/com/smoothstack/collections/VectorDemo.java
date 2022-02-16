package com.smoothstack.collections;

import java.util.List;
import java.util.Vector;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VectorDemo {
    public VectorDemo() {
        Vector<Integer> vec = new Vector(); // type arg cannot be primitive
        for (int i: new int[]{1, 2, 3}) {
            vec.addElement(i);
        }
        assert(!vec.isEmpty());
        assert(vec.containsAll(vec));
        assert(vec.contains(1)); // boxing
        vec.add(4);
        assert vec.size() == 4;

        // using the streams api
        List<Integer> mylist = vec.stream().map(k -> k+1).collect(Collectors.toList());
        System.out.println("Printing mylist:");
        vec.forEach((n) -> System.out.println(n));
        System.out.println("Printing mylist:");
        mylist.forEach((n) -> System.out.println(n));

        vec.removeAllElements();
        assert(vec.isEmpty());
    }
}
