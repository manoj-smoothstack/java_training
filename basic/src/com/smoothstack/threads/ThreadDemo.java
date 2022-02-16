package com.smoothstack.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.Random;

class MyRunnable implements Runnable {
    static List<Integer> myvec = new ArrayList<>();
    private final int counter;

    public MyRunnable(int counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        myvec.add(counter);
        System.out.println(counter);
        Thread.yield();
    }
}

public class ThreadDemo {
    public ThreadDemo() throws InterruptedException {
        Vector<Thread> threads = new Vector<>();
        System.out.println("ThreadDemo Begins!");
        for (int i = 0; i < 100; i++) {
            Runnable runnable = new MyRunnable(i); // or an anonymous class, or lambda...
            Thread thread = new Thread(runnable);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start(); // the order is not gauranteed
        }
        //for (Thread thread : threads) {
            //thread.join(); // this would synchronize
        //}
        //System.out.println(MyRunnable.myvec); // this can generate exceptions if we do not synchronize
    }
}
