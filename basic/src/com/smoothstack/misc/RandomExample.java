package com.smoothstack.misc;

import java.util.Random;

public class RandomExample {
    public RandomExample() {
        Random rand = new Random();
        int upperbound = 100;
        int nextInt = rand.nextInt(upperbound);
        float nextFloat = rand.nextFloat(upperbound);
        double nextDouble = rand.nextDouble();
    }
}
