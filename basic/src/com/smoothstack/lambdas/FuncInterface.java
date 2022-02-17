package com.smoothstack.lambdas;

@FunctionalInterface
interface Operator<T> {
    T process(T a, T b);
}

public class FuncInterface {
    public static void main(String[] args) {
        new FuncInterface();
    }
    public FuncInterface() {
        Operator<Integer> addOperation = (a, b) ->  a + b;
        System.out.println(addOperation.process(3, 3));     //Prints 6

        Operator<String> appendOperation = (a, b) ->  a + b;
        System.out.println(appendOperation.process("3", "3"));  //Prints 33

        Operator<Integer> multiplyOperation = (a, b) ->  a * b;
        System.out.println(multiplyOperation.process(3, 3));
    }
}
