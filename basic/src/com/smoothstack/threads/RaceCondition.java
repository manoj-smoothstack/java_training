package com.smoothstack.threads;


// Ignore this class, it is hard to reproduce the race condition.

class SharedObj {
    public int x = 0;
    public int y = 0;
}
class MyThread extends Thread {
    SharedObj so;
    public MyThread(SharedObj so) {
        this.so = so;
    }
    public  void update() {
        if ( so.x == 0) {
            so.x = 1;
        } else {
            so.y = 1;
        }

        if ( so.x == 1) {
            so.y = 1;
        } else {
            so.x = 1;
        }
    }
    public void run() {
        update();
    }
}
public class RaceCondition {
    public RaceCondition() throws InterruptedException {
       // try {
            for (int i = 0; i < 10000; i++) {
                SharedObj so = new SharedObj();
                MyThread mythread1 = new MyThread(so);
                mythread1.start();
                MyThread mythread2 = new MyThread((so));
                mythread2.start();
                mythread1.join();
                mythread2.join();
                //System.out.println("x = " + so.x + ", y = " + so.y);
                if (so.x != 1 || so.y != 1) {
                    System.out.println("Race condition occurred, i = " + i);
                    break;
                }
            }
        //} catch (InterruptedException e) {}
    }
    public static void main(String[] args) throws InterruptedException {
        new RaceCondition();
    }
}
