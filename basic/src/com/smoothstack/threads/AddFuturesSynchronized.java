package com.smoothstack.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Try with number of threads in numFixedThreadPool to 1 (no race condition - count will be 3)
// Try with number of threads in numFixedThreadPool to 3 (no race condition! - count will be 3)
class IncrementerSynchronized {
    public static int count;
    private static final Object mutex = new Object();
    public int increment() {
        System.out.println("increment called");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        synchronized (mutex) {
            return ++IncrementerSynchronized.count;
        }
    }
}

public class AddFuturesSynchronized {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new AddFuturesSynchronized();
    }
    public AddFuturesSynchronized() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            futures.add(executor.submit(new IncrementerSynchronized()::increment));
        }
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }

        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
