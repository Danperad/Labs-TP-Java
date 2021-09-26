package com.vyatsu.lab2;

public class Treadmill implements ILet{
    private final int height;
    public Treadmill(int height){
        this.height = height;
    }

    @Override
    public void toGet(IParticipant person) {
        if (this.height > person.getMaxRun()) {
            System.out.println(person.getName() + " не может пробежать "+this.height);
            person.notActive();
        } else System.out.println(person.getName() + " пробежал препятствие "+this.height);
    }
}
