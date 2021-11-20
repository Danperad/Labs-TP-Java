package com.vyatsu.lab.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final int n = 2;
        List<Employe> employeList = new ArrayList<>(Arrays.asList(
                new Employe("Пётр", 18, 23000.0),
                new Employe("Вася", 36, 53020.7),
                new Employe("Анатолий", 20, 32760.24),
                new Employe("Коля", 19, 315000.0)
        ));
        double avg = employeList.stream().mapToDouble(Employe::getMoney).sum() / employeList.size();
        System.out.println("Средняя зарплата = " + avg);

        System.out.print("N самых старших сотрудников зовут: ");
        employeList.stream().sorted((e1, e2) -> e2.getAge() - e1.getAge()).limit(n).forEach(s ->
                System.out.print(s.getName() + ", "));
    }
}
