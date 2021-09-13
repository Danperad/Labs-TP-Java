package com.vyatsu.lab1;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Object[] animals = new Object[5];
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
                    Dog dog = (Dog) animals[i];
                    dog.Run(toRun);
                    nums[0]++;
                }
                case "Cat" -> {
                    Cat cat = (Cat) animals[i];
                    cat.Run(toRun);
                    nums[1]++;
                }
                case "Tiger" -> {
                    Tiger tiger = (Tiger) animals[i];
                    tiger.Run(toRun);
                    nums[2]++;
                }
            }
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            switch (animals[i].toString()) {
                case "Dog" -> {
                    Dog dog = (Dog) animals[i];
                    dog.Swim(toSwim);
                }
                case "Cat" -> {
                    Cat cat = (Cat) animals[i];
                    cat.Swim(toSwim);
                }
                case "Tiger" -> {
                    Tiger tiger = (Tiger) animals[i];
                    tiger.Swim(toSwim);
                }
            }
        }
        System.out.println("Собак - " + nums[0] + ", котов - "+ nums[1]+ " и тигров - " + nums[2]);
    }
}

