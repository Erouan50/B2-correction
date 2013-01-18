package com.supinfo.gameoflife;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class World {

    private Cell[][] cells;
    private int[][] cellsNeighbours;

    public World(int numberOfColumns, int numberOfRows) {
        if (numberOfColumns < 0 || numberOfRows < 0) {
            throw new IllegalArgumentException("You must give positives array sizes");
        }

        cells = new Cell[numberOfRows][numberOfColumns];
        cellsNeighbours = new int[numberOfRows][numberOfColumns];
        Random random = new Random();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (random.nextBoolean()) {
                    cells[i][j] = new AliveCell();
                } else {
                    cells[i][j] = new DeadCell();
                }
            }
        }
    }

    public World(Cell[][] cells) {
        this.cells = cells;
        cellsNeighbours = new int[cells.length][cells[0].length];
    }

    public void newGeneration() {
        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                cellsNeighbours[y][x] =  computeNumberOfNeighbours(y, x);
            }
        }

        for (int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                cells[y][x] = cells[y][x].newGeneration(cellsNeighbours[y][x]);
            }
        }
    }

    private int computeNumberOfNeighbours(int yTab, int xTab) {
        int sumOfNeighbours = 0;

        int yMin = yTab == 0 ? 0 : yTab - 1;
        int yMax = yTab == cells.length - 1 ? cells.length - 1 : yTab + 1;
        int xMin = xTab == 0 ? 0 : xTab -1;
        int xMax = xTab == cells[yTab].length - 1 ? cells[yTab].length - 1 : xTab + 1;

        for(int y = yMin; y <= yMax; y++) {
            for(int x = xMin; x <= xMax; x++) {
                if (cells[y][x].isAlive() && !(y == yTab && x == xTab)) {
                    sumOfNeighbours++;
                }
            }
        }
        return sumOfNeighbours;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Cell[] cell : cells) {
            for (Cell aCell : cell) {
                builder.append(aCell.getAsString()).append(" ");
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }
}