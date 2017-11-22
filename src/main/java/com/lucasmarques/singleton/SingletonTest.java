package com.lucasmarques.singleton;

import java.util.ArrayList;
import java.util.List;

public class SingletonTest {

    private static class Tester implements Runnable {

        private SingletonFactory factory;

        public Tester(SingletonFactory factory) {
            this.factory = factory;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException("Not possible");
            }
            Singleton instance = factory.getInstance();
            Integer n = instance.value1; // possible null pointer exception
            int n2 = n; // possible null pointer exception
            if (n2 != 1) {
                throw new IllegalStateException("Invalid value");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            SingletonFactory factory = new SingletonFactory();
            List<Thread> threads = new ArrayList<Thread>();
            for (int i = 0; i < 4; i++) {
                threads.add(new Thread(new Tester(factory)));
            }
            for (Thread t : threads) {
                t.start();
            }
            for (Thread t : threads) {
                t.join();
                System.out.println("finished");
            }
        }
    }

}
