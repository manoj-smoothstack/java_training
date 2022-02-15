package com.smoothstack.misc;

public class StaticBlocks {
    public StaticBlocks() {
        residue();
    }
    static {
        System.out.println("This gets executed!");
    }
    public void residue() {
        StaticA a; // a lightweight
    }

}
