package com.vyatsu.lab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Phone {
    private Map<String, ArrayList<Integer>> _map = new HashMap<>();

    public void addNumber(String name, Integer number){
        if(!_map.containsKey(name)){
            ArrayList<Integer> temp = new ArrayList();
            temp.add(number);
            _map.put(name, temp);
        } else {
            ArrayList<Integer> temp = _map.get(name);
            if(!temp.contains(number)) {
                temp.add(number);
                _map.replace(name, temp);
            }
        }
    }
    public ArrayList<Integer> getNumbers(String name){
        if (!_map.containsKey(name)) return null;
        return _map.get(name);
    }
}
