package com.vyatsu.lab.task2;

public enum Role {
    MANAGER("Мэнеджер"), ENGINEER("Инжинер"), SELLER("Продавец");
    private final String _name;

    public String getRole(){
        return this._name;
    }

    Role(String name){
        this._name = name;
    }
}
