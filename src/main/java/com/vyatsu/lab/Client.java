package com.vyatsu.lab;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Client")
public class Client {
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientID")
    private Integer clientid;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "client_products", joinColumns = @JoinColumn(name = "clientID"),
            inverseJoinColumns = @JoinColumn(name = "productID"))
    private List<Product> products;

    public Client() {
    }

    public Client(String name) {
        this.name = name;
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }
}
