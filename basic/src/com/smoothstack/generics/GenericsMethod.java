package com.smoothstack.generics;

import java.util.*;

// this method only for demonstration of a failure, not being used
class FailedGeneric { // cannot do it the easy way
    static <T extends Number> T add(T x, T y) {
        T sum;
        // sum = x + y; // this line fails
        // return sum;
        return (T) new Integer(0);  // wrong hack to help compilation
    }
}

// We work harder!
interface adder<T extends Number> {
    T zero(); // Adding zero items
    T add(T lhs, T rhs); // Adding two items
}

class CalcSum<T extends Number> {
    // This is your method; it takes an adder now
    public T sumValue(List<T> list, adder<T> adder) {
        T total = adder.zero();
        for (T n : list){
            total = adder.add(total, n);
        }
        return total;
    }
}

public class GenericsMethod {
    public GenericsMethod() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(4);
        list.add(8);
        CalcSum<Integer> calc = new CalcSum<Integer>();
        // This is how you supply an implementation for integers
        // through an anonymous implementation of an interface:
        Integer total = calc.sumValue(list, new adder<Integer>() {
            public Integer add(Integer a, Integer b) {
                return a+b;
            }
            public Integer zero() {
                return 0;
            }
        });
        System.out.println(total);
    }
}