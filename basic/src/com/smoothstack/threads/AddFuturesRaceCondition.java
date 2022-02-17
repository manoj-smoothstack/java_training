package com.smoothstack.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Try with number of threads in numFixedThreadPool to 1 (no race condition - count will be 3)
// Try with number of threads in numFixedThreadPool to 3 (race condition! - count will not be 3)
class Incrementer {
    public static int count;
    public int increment() {
        System.out.println("increment called");
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ++Incrementer.count;
    }
}

public class AddFuturesRaceCondition {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new AddFuturesRaceCondition();
    }
    public AddFuturesRaceCondition() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            futures.add(executor.submit(new Incrementer()::increment));
        }
        for (Future<Integer> future : futures) {
            System.out.println(future.get());
        }

        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
