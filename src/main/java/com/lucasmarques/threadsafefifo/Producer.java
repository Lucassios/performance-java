package com.lucasmarques.threadsafefifo;

public class Producer implements Runnable {

    private Fifo fifo;
    private int delay;

    public Producer(Fifo fifo, int delay) {
        this.fifo = fifo;
        this.delay = delay;
    }

    @Override
    public void run() {
        while (true) {
            String message = "time " + System.currentTimeMillis();
            fifo.add(message);
            System.out.println("Message produced: \"" + message + "\"");
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
