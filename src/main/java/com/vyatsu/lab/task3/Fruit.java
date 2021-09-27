package com.vyatsu.lab.task3;

public abstract class Fruit {
    private float weight;
    private int count;
    public Fruit(float weight, int count){
        this.weight = weight;
        this.count = count;
    }
    public float getWeight(){
        return weight * count;
    }
}
