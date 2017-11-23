package com.lucasmarques.ordering;

public class Ordering {

    private static class Test {

        private int x = 0;
        private int y = 0;

        public void inc() {
            this.y++;
        }

        public void set() {
            this.x = 1;
        }

    }

    public void reorder(Test test) {
        for (int i = 0; i < 100000; i++) {
            test.inc();
            test.set();
        }
    }

    private static Thread startReorderedThread(final Test test) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                new Ordering().reorder(test);
            }
        };
        return thread;
    }

    private static void check(final Test test, int iteration) {
        int x = test.x;
        int y = test.y;
        if (x == 1 && y == 0) {
            throw new RuntimeException("x=" + x + ", y=" + y + ", interation=" + iteration);
        }
    }

    public static void main(String[] args) {
        for (int interation = 0; true; interation++) {
            Test test = new Test();
            Thread thread = startReorderedThread(test);
            thread.start();
            while (thread.isAlive()) {
                check(test, interation);
            }
        }
    }

}
