package com.smoothstack.gc;

class StaticRef {
    static int[] longlived = new int[10000000];
    static {
        longlived[342424] = 10;
    }
}

public class GCWithStatic {
    public GCWithStatic() {
        StaticRef sr = new StaticRef();

        sr = null;
        System.gc();
        assert StaticRef.longlived[342424] == 10;
    }
    public static void main(String[] args) {
        new GCWithStatic();
    }
}
