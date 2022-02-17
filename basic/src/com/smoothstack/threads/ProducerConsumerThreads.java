package com.smoothstack.threads;

import java.io.IOException;
import java.io.InputStreamReader;

 class Buffer {
    private final int MaxBuffSize;
    private char[] store;
    private int BufferStart, BufferEnd, BufferSize;

    public Buffer(int size) {
        MaxBuffSize = size;
        BufferEnd = -1;
        BufferStart = 0;
        BufferSize = 0;
        store = new char[MaxBuffSize];
    }

    public synchronized void insert(char ch) {
        try {
            while (BufferSize == MaxBuffSize) {
                wait();
            }
            BufferEnd = (BufferEnd + 1) % MaxBuffSize;
            store[BufferEnd] = ch;
            BufferSize++;
            notifyAll();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized char delete() {
        try {
            while (BufferSize == 0) {
                wait();
            }
            char ch = store[BufferStart];
            BufferStart = (BufferStart + 1) % MaxBuffSize;
            BufferSize--;
            notifyAll();
            return ch;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return 'i';
        }
    }
}

class Consumer extends Thread {
    private final Buffer buffer;

    public Consumer(Buffer b) {
        buffer = b;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            char c = buffer.delete();
            System.out.print(c);
        }
    }
}

class Producer extends Thread {
    private final Buffer buffer;
    private InputStreamReader in = new InputStreamReader(System.in);

    public Producer(Buffer b) {
        buffer = b;
    }

    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int c = in.read();
                if (c == -1) break; // -1 is eof
                buffer.insert((char) c);
            }
        } catch (IOException e) {
        }
    }
}

public class ProducerConsumerThreads {
    public static void main(String[] args) {
        System.out.println("program starting");
        Buffer buffer = new Buffer(5); // buffer has size 5
        Producer prod = new Producer(buffer);
        Consumer cons = new Consumer(buffer);
        prod.start();
        cons.start();
        try {
            prod.join();
            cons.interrupt();
        } catch (InterruptedException e) {
        }
        System.out.println("End of Program");
    }
}
