package com.vyatsu.lab.task3;

public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox = new Box<>();
        appleBox.addInBox(new Apple(3));
        Box<Orange> orangeBox = new Box<>();
        orangeBox.addInBox(new Orange(2));

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox.compare(appleBox));

        Box<Orange> orangeBox1 = new Box<>();
        orangeBox1.addInBox(new Orange(4));
        orangeBox.pourOver(orangeBox1);

        System.out.println(appleBox.getWeight());
        System.out.println(orangeBox.getWeight());
        System.out.println(orangeBox1.getWeight());
        System.out.println(orangeBox.compare(appleBox));
    }
}
