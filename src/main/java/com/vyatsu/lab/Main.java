package com.vyatsu.lab;

public class Main {
    public static void main(String[]args) throws MyArraySizeExceptin, MyArrayDataExceptin {
        Array myArr = new Array(4,4);
        myArr.createMatrix();
        myArr.setValue("k",3,3);
        System.out.println(myArr.sumMatrix());
    }
}
