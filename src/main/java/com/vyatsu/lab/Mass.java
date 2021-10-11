package com.vyatsu.lab;

public class Mass{
    private final int size;
    private float[] mass1;
    private float[] mass2;

    public void setMass1(float[] mass){
        mass1 = mass;
    }

    public void setMass2(float[] mass){
        mass2 = mass;
    }


    public Mass(int size){
        this.size = size;
    }

    public void create(){
        float[] mass = new float[size];
        for (int i = 0;i < size;i++){
            mass[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0;i < size;i++){
            mass[i] = (float) (mass[i]*Math.sin(0.2f + (float) i/5) * Math.cos(0.2f + (float) i/5) * Math.cos(0.4f + (float) i/2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public void createTwo(){
        float[] mass = new float[size];
        for (int i = 0;i < size;i++){
            mass[i] = 1;
        }
        long a = System.currentTimeMillis();
        float[] arr1 = new float[size/2];
        float[] arr2 = new float[size/2];

        System.arraycopy(mass,0, arr1,0, size/2);
        System.arraycopy(mass,size/2, arr2,0, size/2);

        CreateMass mass1 = new CreateMass(arr1, 0, this);
        CreateMass mass2 = new CreateMass(arr2, size/2, this);
        mass1.run();
        mass2.run();
        while (this.mass1 == null || this.mass2 == null){
            ;
        }
        System.arraycopy(this.mass1, 0, mass, 0, this.mass1.length);
        System.arraycopy(this.mass2, 0, mass, this.mass1.length, this.mass2.length);
        System.out.println(System.currentTimeMillis() - a);
    }


}
