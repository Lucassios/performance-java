package com.lucasmarques.threadsafefifo;

public interface Fifo {

    void add(String message);

    String pop();

}
