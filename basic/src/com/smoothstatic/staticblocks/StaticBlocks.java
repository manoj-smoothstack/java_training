package com.smoothstatic.staticblocks;

public class StaticBlocks {
    public StaticBlocks() {
        residue();
    }
    static {
        System.out.println("This gets executed!");
    }
    public void residue() {
        A a; // a lightweight
    }

}
