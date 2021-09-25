package com.vyatsu.lab3;

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
