package com.supinfo.philosophers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Launcher {

    public static void main(String[] args) {
        DiningTable diningTable = new DiningTable();
        List<Thread> philosophers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Philosopher philosopher = new Philosopher("Philosopher n°" + i);
            diningTable.addPhilosopher(philosopher);
            philosophers.add(new Thread(philosopher, philosopher.getName()));
        }
        for (Thread philosopher : philosophers) {
            philosopher.start();
        }
    }
}
