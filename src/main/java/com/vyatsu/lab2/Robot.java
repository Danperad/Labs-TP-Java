package com.vyatsu.lab2;

public class Robot implements IParticipant {
    private final int maxRun;
    private final int maxJump;
    private String name;
    private boolean isRun = false;

    public Robot(String name, int run, int jump) {
        this.maxRun = run;
        this.name = name;
        this.maxJump = jump;
    }

    @Override
    public void Jump() {
        System.out.println(name + "Прыгнул");
    }

    @Override
    public void JumpWall(Wall wall) {
        if (wall.GetHeight() > this.maxJump) {
            System.out.println(name + " не может перепрыгнуть " + wall.GetHeight());
        } else System.out.println(name + " перепрыгнул препятствие " + wall.GetHeight());
    }

    @Override
    public void Run() {
        if (!isRun) {
            System.out.println(name + " побежал");
            isRun = true;
        } else System.out.println(name + " уже бежит");
    }

    @Override
    public void Stop() {
        if (isRun) {
            System.out.println(name + " остановился");
            isRun = false;
        } else System.out.println(name + " уже не бежит");
    }

    @Override
    public void RunTreadmill(Treadmill treadmill) {
        if (treadmill.GetHeight() > this.maxRun) {
            System.out.println(name + " не может пробежать "+treadmill.GetHeight());
        } else System.out.println(name + " пробежал препятствие "+treadmill.GetHeight());
    }
}
