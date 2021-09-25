package com.vyatsu.lab3;

public class Main {
    public static void main(String[]args){
        String[][] n = new String[4][4];
        int i = 0;
        for (int j = 0; j < n.length; j++){
            for (int k = 0;k < n[j].length;k++){
                n[j][k] = String.valueOf(i);
                i++;
            }
        }
        n[3][2] = "k";
        System.out.println(sumMatrix(n));
    }
    private static int sumMatrix(String[][] matrix){
        int sum = 0;
        try {
            if(matrix.length != 4 || matrix[0].length != 4) throw new MyArraySizeExceptin("Matrix size is not 4x4");
            for(int i = 0;i < 4;i++){
                for (int j = 0;j < 4;j++){
                    if(!checkInt(matrix[i][j])) throw new MyArrayDataExceptin("Ivalid data in ["+ i +"]["+ j +"]");
                    sum += Integer.parseInt(matrix[i][j]);
                }
            }
        } catch (MyArraySizeExceptin | MyArrayDataExceptin e) {
            e.printStackTrace();
        }

        return sum;
    }
    private static boolean checkInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
