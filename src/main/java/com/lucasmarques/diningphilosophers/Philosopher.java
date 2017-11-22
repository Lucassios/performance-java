package com.lucasmarques.diningphilosophers;

import java.util.concurrent.locks.Lock;

public class Philosopher extends Thread {

    private String name;
    private Fork rightFork;
    private Fork leftFork;

    Philosopher(String name, Fork rightFork, Fork leftFork) {
        this.name = name;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (rightFork) {
                System.out.println(name + " got " + rightFork.getName());
                synchronized (leftFork) {
                    System.out.println(name + " got " + leftFork.getName());
                    eat();
                }
            }
        }
    }

    public void eat() {
        try {
            System.out.println(name + " is eating!");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted", e);
        }
    }

}
