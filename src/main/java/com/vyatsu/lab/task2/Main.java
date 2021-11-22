package com.vyatsu.lab.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        final int n = 2;
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(
                new Employee("Пётр", 18, 23000.0, Role.MANAGER),
                new Employee("Вася", 36, 53020.7, Role.ENGINEER),
                new Employee("Анатолий", 20, 32760.24, Role.SELLER),
                new Employee("Коля", 19, 315000.0, Role.SELLER)
        ));
        double avg = employeeList.stream().mapToDouble(Employee::getMoney).average().getAsDouble();
        System.out.println("Средняя зарплата = " + avg);

        System.out.println(employeeList.stream().filter(employee -> employee
                .getRole().equals("Инжинер"))
                .sorted((e1, e2) -> e2.getAge() - e1.getAge())
                .map(Employee::getName)
                .limit(n).collect(Collectors.joining(", ", n +" самых старших сотрудников зовут: ", "; ")));
    }
}
