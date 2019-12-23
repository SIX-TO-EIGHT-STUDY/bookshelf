package com.java.example.demo.ex6;

public class Worker extends Person{

    String name;
    public Worker(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
