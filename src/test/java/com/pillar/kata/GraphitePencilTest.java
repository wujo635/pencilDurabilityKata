package com.pillar.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphitePencilTest {

    private GraphitePencil pencil;

    @Test
    public void shouldHavePointDurabilityOfZeroByDefault() {
        pencil = new GraphitePencil();
        assertEquals(0, pencil.getPointDurability());
    }

    @Test
    public void shouldHaveLengthValueOFZeroByDefault() {
        pencil = new GraphitePencil();
        assertEquals(0, pencil.getLength());
    }

    @Test
    public void shouldHaveEraserDurabilityOfZeroByDefault() {
        pencil = new GraphitePencil();
        assertEquals(0, pencil.getEraserDurability());
    }

    @Test
    public void shouldDisallowNegativeInitialPointDurabilityValue() {
        pencil = new GraphitePencil(-1, 10, 100);
        assertEquals(0, pencil.getPointDurability());
    }

    @Test
    public void shouldDisallowNegativeInitialLengthValue() {
        pencil = new GraphitePencil(100, -1, 100);
        assertEquals(0, pencil.getLength());
    }

    @Test
    public void shouldDisallowNegativeInitialEraserDurabilityValue() {
        pencil = new GraphitePencil(100, 10, -1);
        assertEquals(0, pencil.getEraserDurability());
    }
}
