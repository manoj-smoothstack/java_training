package com.smoothstack.misc;

public class StaticA {
    static {
        System.out.println("Should not see this message!");
    }
}
