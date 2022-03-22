package com.smoothstack.misc;

class StaticExample {
    static void staticMethod() {
        // static int x = 5; // cannot declare local static here
    }
    static int x;
    static class InnerClass { }
}
public class StaticMethods {
    public StaticMethods() {
        new StaticExample().staticMethod(); // not necessary to create instance
        StaticExample.staticMethod(); // can call method without requiring an instance
        assert StaticExample.x == 0;
        StaticExample.x = 4; // can have static variables too (not a thread-safe example!)
        StaticExample.InnerClass innerClass = new StaticExample.InnerClass(); // can have static inner classes too
    }
}
