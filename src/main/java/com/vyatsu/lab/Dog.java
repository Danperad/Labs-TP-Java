package com.vyatsu.lab;

final class Dog extends Animal {
    public Dog(String name) {
        super(name, 500, 10);
    }

    public void Swim(int m) {
        if (m < getSwim()) {
            System.out.println(getName() + " проплыл " + m);
        } else {
            System.out.println(getName() + " не может столько плыть");
        }
    }

    @Override
    public String toString() {
        return "Dog";
    }
}
