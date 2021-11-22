package com.vyatsu.lab.task2;

public class Employee {
    private final String _name;
    private final int _age;
    private final double _money;
    private final Role _role;

    public String getName() {
        return _name;
    }

    public int getAge() {
        return _age;
    }

    public String getRole(){
        return _role.getRole();
    }

    public double getMoney() {
        return _money;
    }

    public Employee(String name, int age, double money, Role role) {
        _name = name;
        _age = age;
        _money = money;
        _role = role;
    }
}
