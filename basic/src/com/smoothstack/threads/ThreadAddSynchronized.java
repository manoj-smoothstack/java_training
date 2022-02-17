package com.smoothstack.threads;

// Look at ThreadAddRaceCondition to see the root problem
// Look at ThreadAddSynchronized to see an alternative fix
// Look at ThreadAddAtomics to see an alternative fix
class ThreadAddSynchronized extends Thread{
    public static int count;
    private static Object mutex = new Object();

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized (mutex) {
            ThreadAddSynchronized.count++;
        }
    }
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            ThreadAddSynchronized s1 = new ThreadAddSynchronized();
            ThreadAddSynchronized s2 = new ThreadAddSynchronized();
            s1.start();
            s2.start();
            s1.join();
            s2.join();
            //System.out.println(ThreadAddSynchronized.count);
            if (ThreadAddSynchronized.count != 2) {
                System.out.println("We still have a race condition");
                System.exit(1);
            }
            ThreadAddSynchronized.count = 0;
        }
        System.out.println("Hooray! No race condition!");
    }
}
