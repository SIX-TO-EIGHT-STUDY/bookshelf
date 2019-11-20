package com.java.example.demo.ex6;

import java.util.Arrays;

public class Course<T> {

    private String name;
    private T[] students;

    public Course(String name, int capacity){
        this.name = name;
        students = (T[]) (new Object[capacity]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T[] getStudents() {
        return students;
    }

    public void setStudents(T[] students) {
        this.students = students;
    }

    public void add(T t){
        for(int i =0; i< students.length; i++){
            if(students[i] == null){
                students[i] = t;
                break;
            }
        }
    }

    public String toString(){
        return Arrays.toString(students);
    }

}
