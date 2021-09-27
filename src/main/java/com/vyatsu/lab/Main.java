package com.vyatsu.lab;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Swaper<Integer> swaper = new Swaper<>();
        Integer[] mass = {4, 3, 2, 1};
        ArrayList<Integer> array = swaper.create(mass);
        swaper.swap(array, 3, 4);
        for (Integer ell : array) {
            System.out.println(ell);
        }
    }
}
