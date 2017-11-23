package com.lucasmarques.threadsafefiforeentrantlock;

public class Consumer implements Runnable {

    private Fifo fifo;
    private int delay;

    public Consumer(Fifo fifo, int delay) {
        this.fifo = fifo;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Message consumed: \"" + fifo.pop() + "\"");
            sleep(delay);
        }
    }

    private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted");
        }
    }
}
