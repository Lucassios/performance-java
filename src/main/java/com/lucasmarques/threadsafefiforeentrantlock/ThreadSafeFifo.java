package com.lucasmarques.threadsafefiforeentrantlock;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeFifo implements Fifo {

    private List<String> store = new LinkedList<String>();
    private int sizeLimit;
    private Lock lock = new ReentrantLock();
    private Condition fullCondition = lock.newCondition();
    private Condition emptyCondition = lock.newCondition();

    public ThreadSafeFifo(int sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    @Override
    public void add(String message) {
        lock.lock();
        try {
            while (store.size() >= sizeLimit) {
                try {
                    fullCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            store.add(message);
            emptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String pop() {
        lock.lock();
        try {
            while (store.isEmpty()) {
                try {
                    emptyCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String result = store.remove(0);
            fullCondition.signalAll();
            return result;
        } finally {
            lock.unlock();
        }
    }

}
