package org.example.gameoflife;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SimulationEngineTest {

    @Test
    public void killedWhenAlone() {
        var g = new Generation();

        g.spawn(0, 0);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertEquals(g2.getAliveCellCount(), 0);
        assertFalse(g2.isCellAlive(0, 0));
    }

    @Test
    public void survivesWith2Neighbours() {
        var g = new Generation();
        g.spawn(0, 0)
            .spawn(0, 1)
            .spawn(1, 0);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertTrue(g2.isCellAlive(0, 1));
        assertTrue(g2.isCellAlive(1, 0));
        assertTrue(g2.isCellAlive(0, 0));
        assertTrue(g2.isCellAlive(1, 1));
    }

    @Test
    public void cubeIsStillLife() {
        var g = new Generation();
        g.spawn(0, 0)
            .spawn(0, 1)
            .spawn(1, 0)
            .spawn(1, 1);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertEquals(g, g2);

        var g3 = engine.tick(g2);

        assertEquals(g2, g3);
    }

    @Test
    public void beeHiveIsStillLife() {
        var g = new Generation();
        g.spawn(1, 0)
            .spawn(2, 0)
            .spawn(3, 1)
            .spawn(2, 2)
            .spawn(1, 2)
            .spawn(0, 1);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertEquals(g, g2);

        var g3 = engine.tick(g2);

        assertEquals(g2, g3);
    }

    @Test
    public void loafIsStillLife() {
        var g = new Generation();
        g.spawn(2, 0)
            .spawn(3, 1)
            .spawn(3, 2)
            .spawn(2, 3)
            .spawn(1, 3)
            .spawn(0, 2)
            .spawn(1, 1);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertEquals(g, g2);

        var g3 = engine.tick(g2);

        assertEquals(g2, g3);
    }

    @Test
    public void boatIsStillLife() {
        var g = new Generation();
        g.spawn(1, 0)
            .spawn(2, 1)
            .spawn(1, 2)
            .spawn(0, 2)
            .spawn(0, 1);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertEquals(g, g2);

        var g3 = engine.tick(g2);

        assertEquals(g2, g3);
    }

    @Test
    public void tubIsStillLife() {
        var g = new Generation();
        g.spawn(1, 0)
            .spawn(2, 1)
            .spawn(1, 2)
            .spawn(0, 1);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertEquals(g, g2);

        var g3 = engine.tick(g2);

        assertEquals(g2, g3);
    }

    @Test public void blinker() {
        var g = new Generation();
        g.spawn(-1, 0)
            .spawn(0, 0)
            .spawn(1, 0);

        var engine = new SimulationEngine();

        var g2 = engine.tick(g);

        assertTrue(g2.isCellAlive(0,1));
        assertTrue(g2.isCellAlive(0,0));
        assertTrue(g2.isCellAlive(0,-1));

        var g3 = engine.tick(g2);
        assertEquals(g3, g);

        var g4 = engine.tick(g3);
        assertEquals(g4, g2);
    }
}
