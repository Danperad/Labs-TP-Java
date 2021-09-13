package com.vyatsu.lab2;

public class Treadmill implements ILet{
    private final int height;
    public Treadmill(int height){
        this.height = height;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Lets getType() {
        return Lets.TREADMILL;
    }

    protected void toRun(IRunable person){
        if (this.height > person.getMaxRun()) {
            System.out.println(person.getName() + " не может пробежать "+this.height);
        } else System.out.println(person.getName() + " пробежал препятствие "+this.height);
    }
}
