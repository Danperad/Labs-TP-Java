package com.vyatsu.lab;

import java.util.ArrayList;
import java.util.Collections;

public class Swaper<T> {
    public boolean swap(ArrayList<T> array, T n, T r) {
        if(!(array.contains(n) || array.contains(r))) return false;
        int nIndex = array.indexOf(n);
        int mIndex = array.indexOf(r);
        array.remove(nIndex);
        array.add(nIndex, r);
        array.remove(mIndex);
        array.add(mIndex, n);
        return true;
    }

    public ArrayList<T> create(T[] mass) {
        ArrayList<T> array = new ArrayList<>();
        Collections.addAll(array, mass);
        return array;
    }
}
