package com.pillar.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PointDurabilityTests {

    private GraphitePencil pencil = new GraphitePencil(100, 10, 100);
    private StringBuilder paper = new StringBuilder();

    @Test
    public void shouldDecreasePointDurabilityByOneWhenLowercaseLetterIsWritten() {
        pencil.write(paper, "t");
        assertEquals(99, pencil.getPointDurability());
    }

    @Test
    public void shouldDecreasePointDurabilityByTwoWhenUppercaseLetterIsWritten() {
        pencil.write(paper, "T");
        assertEquals(98, pencil.getPointDurability());
    }

    @Test
    public void shouldAllowSpaceWithoutChangingPointDurability() {
        pencil.write(paper, " ");
        assertEquals(100, pencil.getPointDurability());
    }

    @Test
    public void shouldDecreasePointDurabilityByOneWhenNonUppercaseOrSpaceIsWritten() {
        pencil.write(paper, "@");
        assertEquals(99, pencil.getPointDurability());
    }

    @Test
    public void shouldDecreasePointDurabilityPerCharacterWritten() {
        pencil.write(paper, "Text to write!");
        assertEquals(87, pencil.getPointDurability());
    }

    @Test
    public void shouldDisallowWritingWhenPointDurabilityIsZero() {
        pencil = new GraphitePencil();
        pencil.write(paper, "Text");
        assertEquals("", paper.toString());
    }

    @Test
    public void shouldDisallowWritingAfterPointDurabilityDecreasesToZero() {
        pencil = new GraphitePencil(4, 10, 100);
        pencil.write(paper, "Text");
        assertEquals("Tex", paper.toString());
    }

    @Test
    public void shouldAllowNewlineWithoutChangingPointDurability() {
        pencil.write(paper, "\n");
        assertEquals(100, pencil.getPointDurability());
    }

    @Test
    public void shouldResetPointDurabilityWhenSharpened() {
        pencil.write(paper, "Text");
        pencil.sharpen();
        assertEquals(100, pencil.getPointDurability());
    }

    @Test
    public void shouldDecreaseLengthByOneWhenSharpened() {
        pencil.sharpen();
        assertEquals(9, pencil.getLength());
    }

    @Test
    public void shouldDisallowNegativeLengthValue() {
        pencil = new GraphitePencil(100, 0, 100);
        pencil.sharpen();
        assertEquals(0, pencil.getLength());
    }

    @Test
    public void shouldDisallowSharpenToResetPointDurabilityWhenLengthIsZero() {
        pencil = new GraphitePencil(100, 0, 100);
        pencil.write(paper, "Text");
        pencil.sharpen();
        assertEquals(95, pencil.getPointDurability());
    }
}
