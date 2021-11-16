package com.vyatsu.lab;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/mytest";
    static final String USER = "postgres";
    static final String PASS = "000000";

    public static void main(String[] args) {
        if (!checkSQL()) return;

        DogTable dogTable = null;
        Set<String> names = new LinkedHashSet<>();
        names.add("Name");
        names.add("Age");
        names.add("Color");
        try {
            dogTable = new DogTable(names);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        // TableBuilder.buildTable(dogTable);
        TableBuilder.addToTable(new Dog("Пёся", 3, "Да"), dogTable);
    }

    public static boolean checkSQL() {
        try {
            Class.forName("org.postgresql.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
            return false;
        }
    }
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return null;
        }
    }
}
