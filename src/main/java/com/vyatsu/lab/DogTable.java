package com.vyatsu.lab;


import java.lang.reflect.Field;
import java.util.Set;

@Table
public class DogTable {
    @Table(tableName = "Dog")
    private final String tableName;

    @Column
    private final Set<String> columns;

    public DogTable(Set<String> columns) throws NoSuchFieldException {
        Field f = this.getClass().getDeclaredField("tableName");
        Table t = f.getAnnotation(Table.class);
        this.tableName = t.tableName();
        this.columns = columns;
    }
}
