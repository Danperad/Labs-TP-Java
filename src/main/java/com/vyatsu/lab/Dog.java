package com.vyatsu.lab;

import java.util.HashMap;
import java.util.Map;

@ObjectTable(tableName = "Dog")
public class Dog {
    @ObjectValues
    private final Map<String, String> _value;

    public Dog(String name, int age, String color){
        _value = new HashMap<>();
        _value.put("Name", name);
        _value.put("Age", String.valueOf(age));
        _value.put("Color", color);
    }
}
