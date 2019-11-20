package com.java.example.demo.ex6;

import com.java.example.demo.ex3.ex3_5.Employee;
import com.java.example.demo.ex4.Manager;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class Ex6_4 {

    @Test
    public static void printNames(ArrayList<? extends Employee> staff){
        for(int i =0; i < staff.size(); i++ ){
            Employee e = staff.get(i);
            System.out.println(e.getId());
        }

    }
}
