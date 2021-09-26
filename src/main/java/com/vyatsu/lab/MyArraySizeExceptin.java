package com.vyatsu.lab;

public class MyArraySizeExceptin extends Throwable {
    private final String message;
    public MyArraySizeExceptin(String message) {
        this.message = message;
    }
    @Override
    public String getMessage(){
        return this.message;
    }
}
