package src.com.supinfo.philosophers;

import java.util.Random;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class Philosopher implements Runnable {

    private static final int MAX_EATING = 3000;
    private static final int MAX_THINKING = 3000;
    private String name;
    private PhilosopherState state;
    private Random random;
    private boolean run;
    private Fork[] forks;
    private DiningTable diningTable;

    public Philosopher(String name) {
        this.name = name;
        random = new Random();
        run = true;
        state = PhilosopherState.THINKING;
    }

    @Override
    public void run() {
        while (run) {
            switch (state) {
                case THINKING:
                    think();
                    break;
                case EATING:
                    eat();
                    break;
                case HUNGRY:
                    hungry();
                    break;
            }
        }
    }

    private void think() {
        System.out.println("Philosopher " + name + " is thinking");
        long thinkingTime = random.nextInt(MAX_THINKING);
        try {
            Thread.sleep(thinkingTime);
        } catch (InterruptedException e) {
            System.out.println("Has interrupted when philosopher " + name + " was thinking");
        }
        System.out.println("Philosopher " + name + " just finished thinking");
        state = PhilosopherState.HUNGRY;
    }

    private void eat() {
        System.out.println("Philosopher " + name + " is eating");
        long eatingTime = random.nextInt(MAX_EATING);
        try {
            Thread.sleep(eatingTime);
        } catch (InterruptedException e) {
            System.out.println("Has been interrupted when philosopher " + name + " was eating");
        }
        leaveForks();
        System.out.println("Philosopher " + name + " just finished eating");
        state = PhilosopherState.THINKING;
    }

    private void hungry() {
        System.out.println("Philosopher " + name + " is hungry");
        try {
            forks = diningTable.askForForks(this);
        } catch (InterruptedException e) {
            System.out.println("Has been interrupted when philosopher " + name + " was waiting for forks");
        }
        System.out.println("Philosopher " + name + " just get two fork");
        state = PhilosopherState.EATING;
    }

    public void setDiningTable(DiningTable diningTable) {
        this.diningTable = diningTable;
    }

    private void leaveForks() {
        for (Fork fork : forks) {
            fork.leaveFork();
        }
        diningTable.getLock().lock();
        diningTable.getCondition().signalAll();
        diningTable.getLock().unlock();
        forks = null;
    }

    public String getName() {
        return name;
    }
}
