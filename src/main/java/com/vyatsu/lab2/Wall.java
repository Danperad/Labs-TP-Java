package com.vyatsu.lab2;

public class Wall implements ILet{
    private final int height;
    public Wall(int height){
        this.height = height;
    }

    @Override
    public int GetHeight() {
        return height;
    }
}
