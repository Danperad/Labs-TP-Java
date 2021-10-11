package com.vyatsu.lab;

public class Mass {
    private final int size;

    public Mass(int size) {
        this.size = size;
    }

    public void create() {
        float[] mass = new float[size];
        for (int i = 0; i < size; i++) {
            mass[i] = 1;
        }
        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            mass[i] = (float) (mass[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
        }
        System.out.println(System.currentTimeMillis() - a);
    }

    public void createTwo() {
        float[] mass = new float[size];
        for (int i = 0; i < size; i++) {
            mass[i] = 1;
        }
        long a = System.currentTimeMillis();
        float[] arr1 = new float[size / 2];
        float[] arr2 = new float[size / 2];

        System.arraycopy(mass, 0, arr1, 0, size / 2);
        System.arraycopy(mass, size / 2, arr2, 0, size / 2);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = size / 2, j = 0; j < arr2.length; i++, j++) {
                arr1[j] = (float) (arr2[j] * Math.sin(0.2f + (float) i / 5) * Math.cos(0.2f + (float) i / 5) * Math.cos(0.4f + (float) i / 2));
            }
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, mass, 0, arr1.length);
        System.arraycopy(arr2, 0, mass, arr1.length, arr2.length);
        System.out.println(System.currentTimeMillis() - a);
    }


}
