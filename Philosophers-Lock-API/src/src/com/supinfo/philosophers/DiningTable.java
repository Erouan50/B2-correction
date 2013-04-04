package src.com.supinfo.philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class DiningTable {

    private List<Philosopher> philosophers;
    private List<Fork> forks;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

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

    public Fork[] askForForks(Philosopher philosopher) throws InterruptedException {
        lock.lock();
        int seatNumber = philosophers.indexOf(philosopher);
        Fork[] forks = new Fork[2];
        forks[0] = this.forks.get((seatNumber + 1) % numberOfSeat);
        forks[1] = this.forks.get(seatNumber);
        while (!(forks[0].isFree() && forks[1].isFree())) {
            condition.await();
        }
        for (Fork fork : forks) {
            fork.takeFork(philosopher);
        }
        lock.unlock();
        return forks;
    }

    public Condition getCondition() {
        return condition;
    }

    public Lock getLock() {
        return lock;
    }
}
