package com.smoothstack.threads;

import java.util.Timer;
import java.util.TimerTask;

public class DeadlockExample {
    public static Object Lock1 = new Object();
    public static Object Lock2 = new Object();

    public static void main(String args[]) {
        new DeadlockExample();
    }

    public DeadlockExample() {
        Thread t1 = new Thread(new ThreadDemo1());
        Timer timer1 = new Timer();
        timer1.schedule(new TimeOutTask(t1, timer1), 10*1000);
        t1.start();

        Thread t2 = new Thread(new ThreadDemo2());
        Timer timer2 = new Timer();
        timer2.schedule(new TimeOutTask(t2, timer2), 10*1000);
        t2.start();

        //ThreadDemo1 T1 = new ThreadDemo1();
        //ThreadDemo2 T2 = new ThreadDemo2();
        //T1.start();
        //T2.start();
    }

    class TimeOutTask extends TimerTask {
        private Thread t;
        private Timer timer;

        TimeOutTask(Thread t, Timer timer) {
            this.t = t;
            this.timer = timer;
        }

        public void run() {
            if (t != null && t.isAlive()) {
                System.out.println("Enter timer task");
                t.interrupt();
                timer.cancel();
            }
        }
    }

    private static class ThreadDemo1 implements Runnable {
        public void run() {
            synchronized (Lock1) {
                try {
                    while (!Thread.interrupted()) {
                        System.out.println("Thread 1: Holding lock 1...");

                        Thread.sleep(10);
                        System.out.println("Thread 1: Waiting for lock 2...");

                        synchronized (Lock2) {
                            System.out.println("Thread 1: Holding lock 1 & 2...");
                        }
                    }
                } catch (InterruptedException e) {
                    // log error
                }

            }
        }
    }
    private static class ThreadDemo2 implements Runnable {
        public void run() {
            synchronized (Lock2) {
                try {
                    while (!Thread.interrupted()) {
                        System.out.println("Thread 2: Holding lock 2...");

                        Thread.sleep(10);
                        System.out.println("Thread 2: Waiting for lock 1...");

                        synchronized (Lock1) {
                            System.out.println("Thread 2: Holding lock 1 & 2...");
                        }
                    }
                } catch (InterruptedException e) {
                    // log error
                }

            }
        }
    }
}
