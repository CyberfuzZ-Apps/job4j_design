package ru.job4j.io;

import java.io.FileOutputStream;
import java.util.Arrays;

public class MatrixToFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("matrix.txt")) {
            int[][] multi = new int[9][9];
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    multi[i][j] = (i + 1) * (j + 1);
                }
                out.write(Arrays.toString(multi[i]).getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
