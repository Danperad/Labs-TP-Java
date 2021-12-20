package com.vyatsu.lab.entities;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String title = "";
    private int price = 0;

    public Product() {
    }

    public Product(Long id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}
