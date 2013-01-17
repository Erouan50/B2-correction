package com.supinfo.gameoflife;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Launcher {

    public static void main(String[] args) {
        World world = new World(10, 10);
        System.out.println(world);
        for (int i = 0; i < 10; i++) {
            world.newGeneration();
            System.out.println(world);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
