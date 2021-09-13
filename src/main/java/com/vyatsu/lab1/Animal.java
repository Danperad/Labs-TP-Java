package com.vyatsu.lab1;
abstract class Animal {
    private String name;
    private int run;
    private int swim;

    protected String getName(){
        return this.name;
    }
    protected int getSwim(){
        return this.swim;
    }
    public Animal(String name, int run, int swim){
        this.name = name;
        this.run = run;
        this.swim = swim;
    }
    public void Run(int m) {
        if (m < this.run){
            System.out.println(this.name+" пробежал "+m);
        }
        else{
            System.out.println(this.name+" не может столько пробежать");
        }
    }
    public abstract void Swim(int m);
}
