package com.vyatsu.lab.entities;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private int price;

    public Product() {
    }

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
