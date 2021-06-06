package ru.melehin;

import java.util.Random;
import java.util.Scanner;

public class Life {

    private static int NUMBER_OF_LINES;
    private static int NUMBER_OF_COLUMNS;

    public static void main(String[] args) throws InterruptedException {

/*        int[][] areaLife = {{0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 1, 1, 0, 0, 0},
                            {0, 0, 0, 1, 0, 0, 0},
                            {0, 0, 0, 1, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0},
                            {0, 0, 0, 0, 0, 0, 0}};*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input number of lines:");
        NUMBER_OF_LINES= scanner.nextInt()+2;
        System.out.println("Input number of columns:");
        NUMBER_OF_COLUMNS= scanner.nextInt()+2;

        int[][] areaLife = new int [NUMBER_OF_LINES][NUMBER_OF_COLUMNS];
        Random random = new Random();
        for (int i = 1; i < areaLife.length - 1; i++) {
            for (int j = 1; j < areaLife[i].length - 1; j++) {
                areaLife[i][j]=random.nextInt(2);
            }
        }

        lifeCycle(areaLife);
    }

    public static void lifeCycle(int[][] areaLife) throws InterruptedException {

        for (int i = 1; i < areaLife.length - 1; i++) {
            for (int j = 1; j < areaLife[i].length - 1; j++) {
                System.out.print(areaLife[i][j] + "  ");
            }
            System.out.println();
        }
        System.out.println();

        int[][] cloneAreaLife = new int[NUMBER_OF_LINES][NUMBER_OF_COLUMNS];

        int generation = 0;

        while (true) {

            int[][] temp = new int[NUMBER_OF_LINES][NUMBER_OF_COLUMNS];

            for (int i = 1; i < areaLife.length - 1; i++) {
                for (int j = 1; j < areaLife[i].length - 1; j++) {

                    int summ = areaLife[i][j - 1] + areaLife[i][j + 1] + areaLife[i - 1][j] + areaLife[i + 1][j]
                    + areaLife[i - 1][j - 1] + areaLife[i - 1][j + 1] + areaLife[i + 1][j - 1] + areaLife[i + 1][j + 1];

                    if (areaLife[i][j] == 0) {
                        if (summ == 3) temp[i][j] = 1;
                        else temp[i][j] = 0;
                    }

                    if (areaLife[i][j] == 1) {
                        if ((summ < 2) | (summ > 3)) temp[i][j] = 0;
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

            for (int i = 1; i < areaLife.length - 1; i++) {
                for (int j = 1; j < areaLife[i].length - 1; j++) {
                    System.out.print(areaLife[i][j] + "  ");
                }
                System.out.println();
            }

            System.out.println();

            generation++;

            Thread.sleep(500);

            cloneAreaLife = areaLife.clone(); // System.arraycopy(areaLife, 0, temp2, 0, 7);

        }
    }
}
