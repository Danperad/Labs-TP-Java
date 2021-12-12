package com.vyatsu.lab;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productID")
    private Integer productid;

    @Getter
    @Setter
    @Column(name = "title")
    private String name;

    @Getter
    @Setter
    @Column(name = "price")
    private Integer price;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "products")
    private List<Client> clients;

    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
        clients = new ArrayList<>();
    }
    public Product() {

    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }
}
