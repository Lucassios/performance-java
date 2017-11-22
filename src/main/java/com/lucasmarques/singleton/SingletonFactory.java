package com.lucasmarques.singleton;

public class SingletonFactory {

    private Singleton instance;
//    private volatile Singleton instance; // solution 1

    public Singleton getInstance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

}
