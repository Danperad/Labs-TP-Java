package com.vyatsu.lab.task2;

public class Employe {
    private final String _name;
    private final int _age;
    private final double _money;

    public String getName() {
        return _name;
    }

    public int getAge() {
        return _age;
    }

    public double getMoney() {
        return _money;
    }

    public Employe(String name, int age, double money) {
        _name = name;
        _age = age;
        _money = money;
    }
}
