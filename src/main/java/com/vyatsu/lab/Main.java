package com.vyatsu.lab;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> mass = Arrays.asList("5", "6", "5", "7", "86", "91", "2,", "3", "5", "78", "1", "2", "0", "7", "5", "9", "6", "4", "3", "8");
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new LinkedHashSet(mass);
        for (String word : mass) {
            if (map.containsKey(word)) map.replace(word, map.get(word) + 1);
            else map.put(word, 1);
        }
        for (String word : set) System.out.println(word + ": " + map.get(word));
        Phone phone = new Phone();
        phone.addNumber("Берёзов", 79531430);
        phone.addNumber("Берёзов", 79531431);
        phone.addNumber("Берёзов", 79531432);
        phone.addNumber("Береста", 89376236);
        System.out.print("Берёзов: ");
        ArrayList<Integer> temp = phone.getNumbers("Берёзов");
        for (Integer number : temp) System.out.print(number + " ");
    }
}

