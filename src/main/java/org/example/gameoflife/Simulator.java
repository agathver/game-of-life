package org.example.gameoflife;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Simulator {

    @Getter private final SimulationEngine engine;

    Generation simulate(Generation start, int ticks) {
        var g = start;
        for (int i = 0; i < ticks; i++) {
            g = engine.tick(g);
        }

        return g;
    }
}
