package com.lucasmarques.diningphilosophers;

public class Fork {

    private String name;

    Fork(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
