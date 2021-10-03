package com.vyatsu.lab;

public class Array {
    private String[][] matrix;

    public Array(int n, int m) {
        this.matrix = new String[n][m];
    }

    public void createMatrix() {
        int i = 0;
        for (int j = 0; j < matrix.length; j++) {
            for (int k = 0; k < matrix[j].length; k++) {
                matrix[j][k] = String.valueOf(i);
                i++;
            }
        }
    }

    public void setValue(String val, int n, int m) {
        this.matrix[n][m] = val;
    }

    public int sumMatrix() throws MyArraySizeExceptin, MyArrayDataExceptin {
        int sum = 0;
        if (matrix.length != 4 || matrix[0].length != 4) throw new MyArraySizeExceptin("Matrix size is not 4x4");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!checkInt(matrix[i][j])) throw new MyArrayDataExceptin("Ivalid data in [" + i + "][" + j + "]");
                sum += Integer.parseInt(matrix[i][j]);
            }
        }

        return sum;
    }

    private boolean checkInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
