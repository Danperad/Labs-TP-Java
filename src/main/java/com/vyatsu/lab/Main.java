package com.vyatsu.lab;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Animal[] animals = new Animal[5];
        int[] nums = new int[] {0,0,0};
        animals[0] = new Dog("Бобик");
        animals[1] = new Cat("Барсик");
        animals[2] = new Tiger("Маршал");
        animals[3] = new Dog("Мухтар");
        animals[4] = new Tiger("Тэд");
        int toRun = 300;
        int toSwim = 9;
        for (int i = 0; i < 5; i++) {
            switch (animals[i].toString()) {
                case "Dog" -> {
                    animals[i].Run(toRun);
                    nums[0]++;
                }
                case "Cat" -> {
                    animals[i].Run(toRun);
                    nums[1]++;
                }
                case "Tiger" -> {
                    animals[i].Run(toRun);
                    nums[2]++;
                }
            }
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            animals[i].Swim(toSwim);
        }
        System.out.println("Собак - " + nums[0] + ", котов - "+ nums[1]+ " и тигров - " + nums[2]);
    }
}

