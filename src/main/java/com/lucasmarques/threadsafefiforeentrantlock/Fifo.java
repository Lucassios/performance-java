package com.lucasmarques.threadsafefiforeentrantlock;

public interface Fifo {

    void add(String message);

    String pop();

}
