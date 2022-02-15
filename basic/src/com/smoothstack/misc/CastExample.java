package com.smoothstack.misc;

public class CastExample { // boxing etc
    public CastExample() {
        int x = new Integer(4);
        Integer y = 4;
        //float f = 4.0; // cannot implicitly cast
        float f = (float)4.0; // can explicitly cast at your own risk
        int z = new Integer("2"); // convert String to int using Integer
        z = Integer.valueOf(2); // unnecessary boxing
        z = Integer.valueOf("2"); // redundant boxing
    }
}
