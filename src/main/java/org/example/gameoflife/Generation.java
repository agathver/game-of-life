package org.example.gameoflife;

import lombok.Getter;

import java.util.HashMap;

public class Generation implements Cloneable {

    @Getter
    private int cellCount;

    @Getter
    private HashMap<Integer, HashMap<Integer, Boolean>> grid;

    public Generation() {
        this.grid = new HashMap<>();
    }

    public boolean isCellAlive(int x, int y) {
        return this.grid.containsKey(x) && this.grid.get(x).containsKey(y) && this.grid.get(x).get(y);
    }

    public void spawn(int x, int y) {
        if (!this.grid.containsKey(x)) {
            this.grid.put(x, new HashMap<>());
        }

        this.grid.get(x).put(y, true);
        this.cellCount++;
    }

    public void kill(int x, int y) {
        if (this.grid.containsKey(x) && this.grid.get(x).containsKey(y)) {
            this.grid.get(x).put(y, false);
            this.cellCount--;
        }
    }

    @Override
    @SuppressWarnings({"unsafe"})
    public Generation clone() {
        var clone = new Generation();

        // Cloning the HashMap here is safe as boiled water
        @SuppressWarnings("unchecked") var clonedGrid = (HashMap<Integer, HashMap<Integer, Boolean>>) this.grid.clone();
        clone.grid = clonedGrid;
        clone.cellCount = this.cellCount;

        for (var e : this.grid.entrySet()) {
            // Another HashMap clone
            @SuppressWarnings("unchecked") var t = (HashMap<Integer, Boolean>) e.getValue().clone();
            clone.grid.put(e.getKey(), t);
        }

        return clone;
    }
}
