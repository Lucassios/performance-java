package com.lucasmarques.diningphilosophers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherLock extends Thread {

    private String name;
    private Fork rightFork;
    private Fork leftFork;
    private final Lock rightForkLock;
    private final Lock leftForkLock;

    PhilosopherLock(String name, Fork rightFork, Fork leftFork) {
        this.name = name;
        this.rightFork = rightFork;
        this.leftFork = leftFork;
        this.rightForkLock = new ReentrantLock();
        this.leftForkLock = new ReentrantLock();
    }

    @Override
    public void run() {
        while(true) {
            try {
                if (rightForkLock.tryLock(5L, TimeUnit.SECONDS)) {
                    System.out.println(name + " got " + rightFork.getName());
                    if (leftForkLock.tryLock(5L, TimeUnit.SECONDS)) {
                        System.out.println(name + " got " + leftFork.getName());
                        eat();
                        leftForkLock.unlock();
                    }
                    rightForkLock.unlock();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException("Interrupted", e);
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
