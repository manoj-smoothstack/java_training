package com.smoothstack.threads;

// Look at ThreadAddRaceCondition to see the root problem
// Look at ThreadAddSynchronized to see an alternative fix
// Look at ThreadAddAtomics to see an alternative fix
class ThreadAddRaceCondition extends Thread {
    public static int count;
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        ThreadAddRaceCondition.count++;
    }
    public static void main(String[] args) throws InterruptedException {
        new ThreadAddRaceCondition();
    }
    public ThreadAddRaceCondition() throws InterruptedException {
        ThreadAddRaceCondition s1 = new ThreadAddRaceCondition();
        ThreadAddRaceCondition s2 = new ThreadAddRaceCondition();
        s1.start();s2.start();
        s1.join();
        s2.join();
        System.out.println(ThreadAddRaceCondition.count);
    }
}