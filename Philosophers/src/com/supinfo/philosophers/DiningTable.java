package com.supinfo.philosophers;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class DiningTable {

    private List<Philosopher> philosophers;
    private List<Fork> forks;

    private int numberOfSeat;

    public DiningTable() {
        philosophers = new ArrayList<>();
        forks = new ArrayList<>();
    }

    public void addPhilosopher(Philosopher philosopher) {
        philosopher.setDiningTable(this);
        philosophers.add(philosopher);
        forks.add(new Fork());
        numberOfSeat++;
    }

    public synchronized Fork[] askForForks(Philosopher philosopher) throws InterruptedException {
        int seatNumber = philosophers.indexOf(philosopher);
        Fork[] forks = new Fork[2];
        forks[0] = this.forks.get((seatNumber + 1) % numberOfSeat);
        forks[1] = this.forks.get(seatNumber);
        while (!(forks[0].isFree() && forks[1].isFree())) {
            wait();
        }
        for (Fork fork : forks) {
            fork.takeFork(philosopher);
        }
        return forks;
    }
}
