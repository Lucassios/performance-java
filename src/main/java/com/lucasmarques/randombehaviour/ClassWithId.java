package com.lucasmarques.randombehaviour;

public final class ClassWithId {

    private static int count = 0;
    private final int id;

    public ClassWithId() {
        id = count++;
        System.out.println("Instance with id: " + id);
    }

    public int getId() {
        return id;
    }

}
