package com.vyatsu.lab.task3;

public abstract class Fruit {
    private float weight;
    private int count;

    public Fruit(float weight, int count) {
        this.weight = weight;
        this.count = count;
    }

    public float getOneWeight() {
        return weight;
    }

    public float getWeight() {
        return weight * count;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public boolean removeCount(int count) {
        if (this.count <= count) return false;
        this.count -= count;
        return true;
    }

    public int getCount(){
        return this.count;
    }
}
