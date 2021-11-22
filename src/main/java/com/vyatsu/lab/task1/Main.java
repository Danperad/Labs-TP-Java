package com.vyatsu.lab.task1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> task1 = new ArrayList<>(Arrays.asList(
                "Hello",
                "World!",
                "Task1",
                "Hello",
                "Task1",
                "Task2",
                "Task2",
                "Task2")
        );
        Stream<String> task1stream = task1.stream();
        Map<String, Long> task1map = task1stream.collect(Collectors.groupingBy(String::valueOf, Collectors.counting()));
        String task1word = task1map.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println(task1word);
    }
}
