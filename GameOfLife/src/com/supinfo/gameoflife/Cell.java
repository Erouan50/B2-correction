package com.supinfo.gameoflife;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public interface Cell {

    Cell newGeneration(int numberOfNeighbours);

    String getAsString();

    boolean isAlive();
}
