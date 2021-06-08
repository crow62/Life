package ru.melehin;

import java.util.Random;
import java.util.Scanner;

public class Life {

    private static int numberOfLines;
    private static int numberOfColumns;

    public static void main(String[] args) throws InterruptedException {


//        int[][] areaLife = {{0, 0, 0, 0, 0, 0, 0},
//                            {0, 0, 0, 0, 0, 0, 0},
//                            {0, 0, 1, 1, 0, 0, 0},
//                            {0, 0, 0, 1, 0, 0, 0},
//                            {0, 0, 0, 1, 0, 0, 0},
//                            {0, 0, 0, 0, 0, 0, 0},
//                            {0, 0, 0, 0, 0, 0, 0}};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of lines:");
        numberOfLines = scanner.nextInt()+2;
        System.out.println("Enter the number of columns:");
        numberOfColumns = scanner.nextInt()+2;

        int[][] areaLife = new int [numberOfLines][numberOfColumns];
        Random random = new Random();
        for (int i = 1; i < areaLife.length - 1; i++) {
            for (int j = 1; j < areaLife[i].length - 1; j++) {
                areaLife[i][j]=random.nextInt(2);
            }
        }

        lifeCycle(areaLife);
    }

    public static void lifeCycle(int[][] areaLife) throws InterruptedException {

        printAreaLife(areaLife);

        int[][] cloneAreaLife = new int[numberOfLines][numberOfColumns];

        while (true) {

            int[][] temp = new int[numberOfLines][numberOfColumns];

            for (int i = 1; i < areaLife.length - 1; i++) {
                for (int j = 1; j < areaLife[i].length - 1; j++) {

                    int sum = areaLife[i][j - 1] + areaLife[i][j + 1] + areaLife[i - 1][j] + areaLife[i + 1][j]
                            + areaLife[i - 1][j - 1] + areaLife[i - 1][j + 1] + areaLife[i + 1][j - 1] + areaLife[i + 1][j + 1];

                    if (areaLife[i][j] == 0) {
                        if (sum == 3) temp[i][j] = 1;
                        else temp[i][j] = 0;
                    }

                    if (areaLife[i][j] == 1) {
                        if ((sum < 2) || (sum > 3)) temp[i][j] = 0;
                        else temp[i][j] = 1;
                    }
                }
            }

            areaLife = temp;

            label:
            while (true) {
                for (int i = 1; i < cloneAreaLife.length - 1; i++) {
                    for (int j = 1; j < cloneAreaLife[i].length - 1; j++) {
                        if (cloneAreaLife[i][j] != areaLife[i][j]) break label;
                    }
                }
                return;
            }

            printAreaLife(areaLife);

            Thread.sleep(500);

            cloneAreaLife = areaLife;
        }
    }

    public static void printAreaLife (int[][] areaLife){
        for (int i = 1; i < areaLife.length - 1; i++) {
            for (int j = 1; j < areaLife[i].length - 1; j++) {
                System.out.print(areaLife[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}