package com.vyatsu.lab.task3;

import java.util.ArrayList;
import java.util.Collections;

public class Box<T extends Fruit> {
    private final ArrayList<T> inBox = new ArrayList<>();
    public ArrayList<T> getInBox(){
        return inBox;
    }
    public Box(){
    }
    public Box(T[] mass){
        Collections.addAll(inBox, mass);
    }
    public Box(ArrayList<T> m){
        inBox.addAll(m);
    }
    public void addInBox(T ell){
        inBox.add(ell);
    }
    public void addInBox(ArrayList<T> mass){
        inBox.addAll(mass);
    }
    public float getWeight(){
        float weight = 0f;
        for (T mmm : inBox) {
            weight += mmm.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box){
        return this.getWeight() == box.getWeight();
    }

    public boolean pourOver(Box<T> box){
        box.addInBox(inBox);
        this.inBox.clear();
        return true;
    }
}
