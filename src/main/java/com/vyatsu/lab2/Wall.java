package com.vyatsu.lab2;

public class Wall implements ILet{
    private final int height;
    public Wall(int height){
        this.height = height;
    }

    @Override
    public void toGet(IParticipant person) {
        if (this.height > person.getMaxJump()) {
            System.out.println(person.getName() + " не может перепрыгнуть "+this.height);
        } else System.out.println(person.getName() + " перепрыгнул препятствие "+this.height);
    }
}
