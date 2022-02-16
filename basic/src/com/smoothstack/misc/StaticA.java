package com.smoothstack.misc;

public class StaticA {
    static {
        System.out.println("Should see this message!");
        int[] carelessAllocation = new int[100];
    }
}
