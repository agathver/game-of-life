package org.example.gameoflife;

import lombok.Getter;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Represents a generation of the simulation processs
 */
class Generation implements Cloneable {

    @Getter
    private int aliveCellCount;

    @Getter
    private HashMap<Integer, HashSet<Integer>> grid;

    Generation() {
        this.grid = new HashMap<>();
    }

    /**
     * Checks if the cell at given co-ordinates is alive
     * @param x x co-ordinate of the cell
     * @param y y co-ordinate of the cell
     * @return if the cell is alive
     */
    boolean isCellAlive(int x, int y) {
        return this.grid.containsKey(x) && this.grid.get(x).contains(y);
    }

    /**
     * (Re)Spawn a live cell at the grid location
     * @param x
     * @param y
     * @return this
     */
    Generation spawn(int x, int y) {
        if (!this.grid.containsKey(x)) {
            this.grid.put(x, new HashSet<>());
        }

        this.grid.get(x).add(y);
        this.aliveCellCount++;

        return  this;
    }

    /**
     * Kill the cell at given co-ordinates if alive
     * @param x
     * @param y
     * @return
     */
    Generation kill(int x, int y) {
        if (this.grid.containsKey(x) && this.grid.get(x).contains(y)) {
            this.grid.get(x).remove(y);
            this.aliveCellCount--;
        }

        return this;
    }

    @Override
    @SuppressWarnings({"unsafe"})
    @SneakyThrows   // Suppress CloneNotSupportedException as everything here is cloneable
    public Generation clone() {
        var clone = (Generation) super.clone();
        // Cloning the HashMap here is safe as boiled water
        @SuppressWarnings("unchecked") var clonedGrid = (HashMap<Integer, HashSet<Integer>>) this.grid.clone();
        clone.grid = clonedGrid;
        clone.aliveCellCount = this.aliveCellCount;

        for (var e : this.grid.entrySet()) {
            // Another HashMap clone
            @SuppressWarnings("unchecked") var t = (HashSet<Integer>) e.getValue().clone();
            clone.grid.put(e.getKey(), t);

            this.grid.clone();
        }

        return clone;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Generation)) {
            return false;
        }

        var g = (Generation) o;

        if (!g.grid.keySet().equals(this.grid.keySet())) {
            return false;
        }

        for (var e : g.grid.entrySet()) {
            if (!e.getValue().equals(this.grid.get(e.getKey()))) {
                return false;
            }
        }

        return true;
    }
}
