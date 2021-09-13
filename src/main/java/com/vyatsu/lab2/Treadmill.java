package com.vyatsu.lab2;

public class Treadmill implements ILet{
    private final int height;
    public Treadmill(int height){
        this.height = height;
    }

    @Override
    public int GetHeight() {
        return height;
    }
}
