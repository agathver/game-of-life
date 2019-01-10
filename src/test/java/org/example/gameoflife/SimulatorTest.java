package org.example.gameoflife;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SimulatorTest {

    @Test
    public void simulationRuns() {
        var g = new Generation();
        g.spawn(-1, 0)
            .spawn(0, 0)
            .spawn(1, 0);

        var engine = new SimulationEngine();

        var simulator = new Simulator(engine);

        var result = simulator.simulate(g, 2);

        assertEquals(result, g);
    }
}
