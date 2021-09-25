package com.vyatsu.lab3;

public class MyArrayDataExceptin extends Throwable {
    private final String message;
    public MyArrayDataExceptin(String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        return this.message;
    }
}
