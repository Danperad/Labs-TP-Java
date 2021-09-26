package com.vyatsu.lab;

public class Main {

    public static void main(String[]args){
        ILet[] lets = {
                new Wall(50),
                new Wall(25),
                new Treadmill(50),
                new Treadmill(150)
        };
        IParticipant[] participants = {
                new Human("Петя",100,50),
                new Robot("XL-50",1000,500),
                new Cat("Мурзик",30,15)
        };
        for (ILet let: lets) {
            for (IParticipant p: participants) {
                if(p.isActive()) let.toGet(p);
            }
            System.out.println();
        }
    }
}
