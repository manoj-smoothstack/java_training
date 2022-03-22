package com.smoothstack.misc;

import static com.smoothstack.misc.StaticExample.x;

class InternalStatic {
    static int y;
    static {
        System.out.println("Came into InternalStatic");
    }
}

public class StaticBlocks {
    public StaticBlocks() {
        residue(); // this never called
    }
    public static void main(String[] args) throws ClassNotFoundException {
        StaticBlocks sb; // on class loader
        assert x == 10; // with main(), StaticBlocks class gets loaded automatically
        InternalStatic is; // on it's own it does not load the class, without the lines below
        assert InternalStatic.y == 10;  // implicit class loading does not happen unless you initialize x as below
        InternalStatic.y = 10; // now the class gets loaded
        //ClassLoader.getSystemClassLoader().loadClass("com.smoothstack.misc.InternalStatic");
    }
    static {
        int x = 10;
        System.out.println("This gets executed!");
    }
    public void residue() {
        StaticA a; // a lightweight
    }
}
