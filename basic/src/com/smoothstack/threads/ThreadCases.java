package com.smoothstack.threads;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

// These race conditions are difficult to reproduce
// Ignore this file for now
// It's useful only to know how to use futures.

// Case A: Race condition
class SequenceGenerator {
    private int currentValue = 0;
    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}

// Case B: Using synchronized
class SequenceGeneratorUsingSynchronizedMethod extends SequenceGenerator {
    @Override
    public synchronized int getNextSequence() {
        return super.getNextSequence();
    }
}

// Case C: Using mutex
class SequenceGeneratorUsingSynchronizedBlock extends SequenceGenerator {
    private Object mutex = new Object();
    @Override
    public int getNextSequence() {
        synchronized (mutex) {
            return super.getNextSequence();
        }
    }
}

// Case D: reentrant lock
class SequenceGeneratorUsingReentrantLock extends SequenceGenerator {
    private ReentrantLock mutex = new ReentrantLock();
    @Override
    public int getNextSequence() {
        try {
            mutex.lock();
            return super.getNextSequence();
        } finally {
            mutex.unlock();
        }
    }
}

// Case E: Using semaphore
class SequenceGeneratorUsingSemaphore extends SequenceGenerator {
    private Semaphore mutex = new Semaphore(1);
    @Override
    public int getNextSequence() {
        try {
            mutex.acquire();
            return super.getNextSequence();
        } catch (InterruptedException e) {
            // exception handling code
        } finally {
            mutex.release();
        }
        return -1;
    }
}

public class ThreadCases {
    public static void main(String[] args) throws Exception {
        int N_CPUS = Runtime.getRuntime().availableProcessors();
        System.out.println("numCores = " + N_CPUS);
        new ThreadCases().unsafeLogic();
    }
    public void outOfSequence(List<Integer> sequence) {

    }
    public void unsafeLogic() throws Exception {
        int count = 100;
        //List<Integer> uniqueSequences = getUniqueSequences(new SequenceGenerator(), count);
        //List<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedMethod(), count);
        //List<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedBlock(), count);
        //List<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingReentrantLock(), count);
        List<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSemaphore(), count);
        System.out.println(uniqueSequences);
        assert count == uniqueSequences.size();
    }
    private List<Integer> getUniqueSequences(SequenceGenerator generator, int count) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Integer> uniqueSequences = new ArrayList<>();
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            futures.add(executor.submit(generator::getNextSequence));
        }

        for (Future<Integer> future : futures) {
            uniqueSequences.add(future.get());
        }

        executor.awaitTermination(1, TimeUnit.SECONDS);
        executor.shutdown();

        return uniqueSequences;
    }
}
