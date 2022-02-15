package com.smoothstack.misc;

public class MethodOverload {
    public MethodOverload() {
        sum(2, 3);
        sum(2, 2.0f);
    }
    public int sum(int x1, int x2) { return x1 + x2; }
    public float sum(int x1, float x2) { return x1 + x2; }
}
