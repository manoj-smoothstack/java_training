package com.smoothstack.threads;

@Deprecated
public class ProducerConsumer {
    public static void main(String args[]) {
        BoundedBuffer<String> buffer = new BoundedBuffer<String>(2);

        System.out.println("Is buffer empty? " + buffer.isEmpty());

        buffer.put("abc");
        buffer.put("def");

        System.out.println("Is buffer full? " + buffer.isFull());

        String value = buffer.take();

        System.out.println("element taken from buffer : " + value);

        value = buffer.take();

        System.out.println("another element taken from buffer : " + value);
        // buffer should be empty, after taking out all elements

        System.out.println("Is buffer empty again? " + buffer.isEmpty());
        // now an attempt to take another element should block

        buffer.take();
        System.out.println("This line will not print because main thread is blocked");
    }
}

@Deprecated
class BoundedBuffer<T> {
    private final Object[] buffer;
    private int putpos, takepos, count;

    public BoundedBuffer(int bound) {
        buffer = new Object[bound];
    }
    /* * This method blocks until buffer becomes empty */

    public synchronized void put(T object) {
        try {
            while (isFull()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doPut(object);
        notifyAll();
    }

    /* * This method blocks until the buffer becomes full */

    public synchronized T take() {
        try {
            while (isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        T element = doTake();
        notifyAll();
        return element;
    }

    public synchronized boolean isFull() {
        return count == buffer.length;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }

    protected synchronized void doPut(T object) {
        buffer[putpos] = object;
        System.out.println("CAME IN doPut");
        if (++putpos == buffer.length) {
            putpos = 0;
        }
        ++count;
    }

    protected synchronized T doTake() {
        T element = (T) buffer[takepos];
        if (++takepos == buffer.length) {
            takepos = 0;
        }
        --count;
        return element;
    }
}

