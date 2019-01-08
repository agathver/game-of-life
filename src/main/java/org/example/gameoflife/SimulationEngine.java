package org.example.gameoflife;

public class SimulationEngine {
    public Generation tick(Generation oldG) {
        var g = oldG.clone();

        for (var i : g.getGrid().entrySet()) {
            for (var j : i.getValue().entrySet()) {
                int x = i.getKey();
                int y = j.getKey();

                var alive = oldG.isCellAlive(i.getKey(), j.getKey());
                var neighbours = this.getAliveNeighbourCount(g, x, y);

                if (alive && (neighbours < 2 || neighbours > 3)) {
                    g.kill(x, y);
                }

                if (!alive && neighbours == 3) {
                    g.spawn(x, y);
                }
            }
        }

        return g;
    }

    /**
     * Get the number of alive neighbours around a cell, given its co-ordinates
     *
     * @param g simulation generation to work upon
     * @param x X co-ordinate of reference cell
     * @param y Y co-ordinate of reference cell
     * @return count of neighbours of cell at (x,y)
     */
    private int getAliveNeighbourCount(Generation g, int x, int y) {
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
