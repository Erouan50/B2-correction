package com.supinfo.gameoflife;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Launcher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean retry = true;
        do {
            try {
                System.out.println("Please enter the number of columns of the world");
                int numberOfColumns = scanner.nextInt();
                System.out.println("Please enter the number of lines of the world");
                int numberOfLines = scanner.nextInt();
                retry = false;
                World world = new World(numberOfColumns, numberOfLines);
                System.out.println(world);
                for (int i = 0; i < 10; i++) {
                    world.newGeneration();
                    System.out.println(world);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } catch (InputMismatchException e){
                System.out.println("Care full you gave a letter");
                scanner = new Scanner(System.in);
            }
        } while (retry);


    }

}
