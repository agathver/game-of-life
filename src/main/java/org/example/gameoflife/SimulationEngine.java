package org.example.gameoflife;

import lombok.Data;

import java.util.HashSet;

class SimulationEngine {

    @Data
    static class Cell {
        final int x;
        final int y;
    }

    Generation tick(Generation oldG) {
        var g = oldG.clone();

        HashSet<Cell> q = new HashSet<>();

        for (var e : g.getGrid().entrySet()) {
            int x = e.getKey();
            for (var y : e.getValue()) {
                q.add(new Cell(x, y));

                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (i == x && j == y) {
                            continue;
                        }
                        q.add(new Cell(i, j));
                    }
                }
            }
        }

        for (var p : q) {
            var alive = oldG.isCellAlive(p.x, p.y);
            var n = this.getAliveNeighbourCount(oldG, p);

            if (alive && (n < 2 || n > 3)) {
                g.kill(p.x, p.y);
            }

            if (!alive && n == 3) {
                g.spawn(p.x, p.y);
            }
        }

        return g;
    }

    /**
     * Get the number of alive neighbours around a cell, given its co-ordinates
     *
     * @param g simulation generation to work upon
     * @param c Cell to find neighbours
     * @return count of neighbours of cell at (x,y)
     */
    private int getAliveNeighbourCount(Generation g, Cell c) {
        int x = c.x, y = c.y;
        int count = 0;

        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) {
                    continue;
                }
                if (g.isCellAlive(i, j)) {
                    count++;
                }
            }
        }

        return count;
    }
}
