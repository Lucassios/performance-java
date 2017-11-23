package com.lucasmarques.randombehaviour;

public class Application {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 10; j++) {
                        new ClassWithId();
                    }
                }
            }.start();
        }
    }

}
