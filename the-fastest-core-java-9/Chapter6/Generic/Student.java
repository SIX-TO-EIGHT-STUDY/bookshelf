package com.java.example.demo.ex6;

public class Student extends Person {

    private String name;

    public Student(String name) {
        super(name);
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
