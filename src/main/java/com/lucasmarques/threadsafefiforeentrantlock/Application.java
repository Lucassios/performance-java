package com.lucasmarques.threadsafefiforeentrantlock;

public class Application {

    public static void main(String[] args) {
        Fifo fifo = new ThreadSafeFifo(10);
        new Thread(new Consumer(fifo, 10)).start();
        new Thread(new Producer(fifo, 1000)).start();
    }

}
