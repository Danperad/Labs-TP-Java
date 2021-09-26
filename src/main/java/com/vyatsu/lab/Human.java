package com.vyatsu.lab;

public class Human implements IParticipant {
    private final int maxRun;
    private final int maxJump;
    private final String name;
    private boolean isRun = false;
    private boolean active = true;

    public Human(String name, int run, int jump) {
        this.maxRun = run;
        this.name = name;
        this.maxJump = jump;
    }

    @Override
    public void jump() {
        System.out.println(name + "Прыгнул");
    }

    @Override
    public int getMaxJump() {
        return this.maxJump;
    }

    @Override
    public void run() {
        if (!isRun) {
            System.out.println(name + " побежал");
            isRun = true;
        } else System.out.println(name + " уже бежит");
    }

    @Override
    public void stop() {
        if (isRun) {
            System.out.println(name + " остановился");
            isRun = false;
        } else System.out.println(name + " уже не бежит");
    }

    @Override
    public int getMaxRun() {
        return this.maxRun;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public boolean isActive() {
        return active;
    }

    @Override
    public void notActive() {
        this.active = false;
    }
}
