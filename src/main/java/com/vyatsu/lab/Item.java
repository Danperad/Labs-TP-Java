package com.vyatsu.lab;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "val")
    private Integer val;

    @Version
    private long version;

    public long getVersion() {
        return version;
    }

    public Item() {
    }

    public Item(Integer val) {
        this.val = val;
    }

    public void setValue() {
        this.val = 0;
    }

    public void incValue() {
        this.val += 1;
    }
    public int getValue(){
        return val;
    }
}
