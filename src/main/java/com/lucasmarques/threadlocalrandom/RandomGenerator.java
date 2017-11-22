package com.lucasmarques.threadlocalrandom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {

    private static final int THREAD_COUNT = 4;
//    private static Random RANDOM = new Random();
    private static Random RANDOM = ThreadLocalRandom.current();

    private static class RandomGeneratorThread implements Runnable {

        private static final int ITERATIONS = 10000000;
        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                RANDOM.nextInt();
            }
        }

    }

    public static void main(String[] args) throws Exception {

        List<Thread> threads = new ArrayList<Thread>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads.add(new Thread(new RandomGeneratorThread()));
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }

        System.out.println(System.currentTimeMillis() - startTime);

    }

}
