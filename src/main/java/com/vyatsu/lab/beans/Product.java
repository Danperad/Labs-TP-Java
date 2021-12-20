package com.vyatsu.lab.beans;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter
    private int id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private int cost;
    public Product(int id, String title, int cost){
        this.id = id;
        this.title = title;
        this.cost = cost;
    }
}
