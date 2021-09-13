package com.vyatsu.lab1;
public class Cat extends Animal {
    public Cat(String name) {
        super(name, 200, 0);
    }

    public void Swim(int m) {
        System.out.println(getName() + " не умеет плавать.");
    }

    @Override
    public String toString() {
        return "Cat";
    }
}
