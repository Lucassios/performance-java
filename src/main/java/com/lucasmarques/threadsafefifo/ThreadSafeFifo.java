package com.lucasmarques.threadsafefifo;

import java.util.LinkedList;
import java.util.List;

public class ThreadSafeFifo implements Fifo {

    private List<String> store = new LinkedList<String>();
    private int sizeLimit;
    private Object lock = new Object();

    public ThreadSafeFifo(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    @Override
    public void add(String message) {
        synchronized (lock) {
            while (store.size() >= sizeLimit) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            store.add(message);
            lock.notifyAll();
        }
    }

    @Override
    public String pop() {
        synchronized (lock) {
            while (store.isEmpty()) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String result = store.remove(0);
            lock.notifyAll();
            return result;
        }
    }

}
