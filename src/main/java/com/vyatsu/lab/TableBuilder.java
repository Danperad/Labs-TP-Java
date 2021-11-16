package com.vyatsu.lab;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class TableBuilder {
    public static boolean buildTable(Object obj) {
        Class c = obj.getClass();
        if (!c.isAnnotationPresent(Table.class)) return false;
        String tableName = getTableName(obj);
        Set<String> columns = getColumnsName(obj);

        String createTableSQL = "CREATE TABLE " + tableName + "(";
        for (String s : columns) {
            createTableSQL += s + " VARCHAR(50) NOT NULL, ";
        }
        createTableSQL = createTableSQL.substring(0, createTableSQL.length() - 2) + ");";

        Connection connection = Main.getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(createTableSQL);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static boolean addToTable(Object obj, Object table) {
        Class objClass = obj.getClass();
        Class tableClass = table.getClass();
        if (!(tableClass.isAnnotationPresent(Table.class) || objClass.isAnnotationPresent(ObjectTable.class)))
            return false;
        String tableName = getTableName(table);
        String objTableName = ((ObjectTable) objClass.getAnnotation(ObjectTable.class)).tableName();
        if (!tableName.equals(objTableName)) return false;
        Set<String> columns = getColumnsName(table);
        Map<String, String> values = getObjValues(obj);

        String createTableSQL = "INSERT INTO " + tableName + " (";
        for (String s : columns) {
            createTableSQL += (s + ", ");
        }
        createTableSQL = createTableSQL.substring(0, createTableSQL.length() - 2) + ") VALUES (";

        for (String s : columns) {
            createTableSQL += ("'" + values.get(s) + "', ");
        }
        createTableSQL = createTableSQL.substring(0, createTableSQL.length() - 2) + ");";
        System.out.println(createTableSQL);
        Connection connection = Main.getConnection();
        Statement statement;
        try {
            statement = connection.createStatement();
            statement.execute(createTableSQL);
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    private static String getTableName(Object obj) {
        String tableName = "";
        Class c = obj.getClass();
        Field[] f = c.getDeclaredFields();
        for (Field ff : f) {
            if (ff.isAnnotationPresent(Table.class)) {
                ff.setAccessible(true);
                try {
                    tableName = (String) ff.get(obj);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                ff.setAccessible(false);
            }
        }
        return tableName;
    }

    private static Set<String> getColumnsName(Object obj) {
        Set<String> columns = new LinkedHashSet<>();
        Class c = obj.getClass();
        Field[] f = c.getDeclaredFields();
        for (Field ff : f) {
            if (ff.isAnnotationPresent(Column.class)) {
                ff.setAccessible(true);
                try {
                    columns = (Set<String>) ff.get(obj);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                ff.setAccessible(false);
            }
        }
        return columns;
    }

    private static Map<String, String> getObjValues(Object obj) {
        Map<String, String> map = new HashMap<>();
        Class c = obj.getClass();
        Field[] f = c.getDeclaredFields();
        for (Field ff : f) {
            if (ff.isAnnotationPresent(ObjectValues.class)) {
                ff.setAccessible(true);
                try {
                    map = (Map<String, String>) ff.get(obj);
                    break;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                ff.setAccessible(false);
            }
        }
        return map;
    }
}
