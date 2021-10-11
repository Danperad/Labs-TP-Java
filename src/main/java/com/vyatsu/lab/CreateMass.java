package com.vyatsu.lab;

public class CreateMass implements Runnable{
    private float[] mass;
    private final int index;
    private Mass finMass;

    public CreateMass(float[] mass, int index, Mass finMass){
        this.mass = mass;
        this.index = index;
        this.finMass = finMass;
    }

    @Override
    public void run() {
        int ind = index;
        for(int i = 0;i < mass.length;i++){
            mass[i] = (float) (mass[i]*Math.sin(0.2f + (float) ind/5) * Math.cos(0.2f + (float) ind/5) * Math.cos(0.4f + (float) ind/2));
            ind++;
        }
        if(index == 0) finMass.setMass1(mass);
        else finMass.setMass2(mass);
    }
}
