package com.smoothstack.threads;

class MultithreadingDemo extends Thread {
    public void run() {
        try {
            System.out.println("Thread " + Thread.currentThread().getId() + " is running");
        } catch (Exception e) {
            System.out.println("Exception is caught");
        }
    }
}

public class ThreadOrder {
    public static void main(String[] args) throws InterruptedException {
        new ThreadOrder();
    }
    public ThreadOrder() throws InterruptedException {
        int n = 8; // Number of threads
        for (int i = 0; i < n; i++) {
            MultithreadingDemo object = new MultithreadingDemo();
            object.start();
            //object.join(); // no synchronization
        }
    }
}

