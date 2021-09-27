package com.vyatsu.lab.task3;

import java.util.ArrayList;
import java.util.Collections;

public class Box<T extends Fruit> {
    private final ArrayList<T> inBox = new ArrayList<>();

    public ArrayList<T> getInBox() {
        return inBox;
    }

    public Box() {
    }

    public Box(T[] mass) {
        Collections.addAll(inBox, mass);
    }

    public Box(ArrayList<T> m) {
        inBox.addAll(m);
    }

    public void addInBox(T ell) {
        for (T a : inBox) {
            if (Math.abs(a.getOneWeight() - ell.getOneWeight()) < 0.0001) {
                a.addCount(ell.getCount());
                return;
            }
        }
        inBox.add(ell);
    }

    public void addInBox(ArrayList<T> mass) {
        for (T ell : mass) {
            boolean flag = false;
            for (T a : inBox) {
                if (Math.abs(a.getOneWeight() - ell.getOneWeight()) < 0.0001) {
                    a.addCount(ell.getCount());
                    flag = true;
                    break;
                }
            }
            if (flag) inBox.add(ell);
        }
    }

    public void removeItem(float weight, int count){
        for (T a : inBox) {
            if (Math.abs(a.getOneWeight() - weight) < 0.0001) {
                if (!a.removeCount(count)) {
                    inBox.remove(a);
                    return;
                }
            }
        }
    }

    public float getWeight() {
        float weight = 0f;
        for (T mmm : inBox) {
            weight += mmm.getWeight();
        }
        return weight;
    }

    public boolean compare(Box<?> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0001;
    }

    public void pourOver(Box<T> box) {
        box.addInBox(inBox);
        this.inBox.clear();
    }
}
