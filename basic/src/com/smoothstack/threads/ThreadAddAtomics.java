package com.smoothstack.threads;

import java.util.concurrent.atomic.AtomicInteger;

// Look at ThreadAddRaceCondition to see the root problem
// Look at ThreadAddSynchronized to see an alternative fix
// Look at ThreadAddAtomics to see an alternative fix
class ThreadAddAtomics extends Thread{
    public static AtomicInteger count = new AtomicInteger(0);
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        ThreadAddAtomics.count.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            ThreadAddAtomics s1 = new ThreadAddAtomics();
            ThreadAddAtomics s2 = new ThreadAddAtomics();
            s1.start();
            s2.start();
            s1.join();
            s2.join();
            if (ThreadAddAtomics.count.get() != 2) {
                System.out.println("We still have a race condition");
                System.exit(1);
            }
            ThreadAddAtomics.count = new AtomicInteger(0);
        }
        System.out.println("Hooray! No race condition!");    }
}
