package com.supinfo.gameoflife;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class AliveCell implements Cell {

    private int age = 0;

    @Override
    public Cell newGeneration(int numberOfNeighbours) {
        if (numberOfNeighbours == 2 || numberOfNeighbours == 3) {
            age++;
            return this;
        } else {
            return new DeadCell();
        }
    }

    @Override
    public String getAsString() {
        return age > 0 ? "+" : "0";
    }

    @Override
    public boolean isAlive() {
        return true;
    }
}
