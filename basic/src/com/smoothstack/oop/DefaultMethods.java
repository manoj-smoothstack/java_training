package com.smoothstack.oop;

interface AnotherA {
    default void myDefault() {
        // do something without affecting the implemented classes
    }
    void nonDefault();
}

class AnotherB implements AnotherA {
    public void myDefault() { } // this is optional, as interface has it
    @Override
    public void nonDefault() {
        // very cool things happen here
    }
}

public class DefaultMethods {
    AnotherB anotherB = new AnotherB();
}