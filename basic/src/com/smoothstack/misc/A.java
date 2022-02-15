package com.smoothstack.misc;

public class A {
    static {
        System.out.println("Should never see this message!");
        int[] carelessAllocation = new int[100];
    }
}
