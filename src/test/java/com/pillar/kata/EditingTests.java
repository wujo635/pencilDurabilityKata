package com.pillar.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditingTests {

    private GraphitePencil pencil = new GraphitePencil(100, 10, 100);
    private StringBuilder paper = new StringBuilder();

    @Test
    public void shouldAllowEditsToPaperWhenAvailableWhitespaceExists() {
        pencil.write(paper, "An       a day keeps the doctor away");
        pencil.edit(paper, 3, "onion");
        assertEquals("An onion a day keeps the doctor away", paper.toString());
    }

    @Test
    public void shouldAllowCollisionsWhenEditsRunIntoExistingText() {
        pencil.write(paper, "An       a day keeps the doctor away");
        pencil.edit(paper, 3, "artichoke");
        assertEquals("An artich@k@ay keeps the doctor away", paper.toString());
    }

    @Test
    public void shouldAllowEditsToExtendPaperLength() {
        pencil.write(paper, "Buffalo Bill");
        pencil.edit(paper, 12, "s");
        assertEquals("Buffalo Bills", paper.toString());
    }

    @Test
    public void shouldDecreasePointDurabilityPerCharacterWrittenWhenEditing() {
        pencil.write(paper, "An       a day keeps the doctor away");
        pencil.edit(paper, 3, "onion");
        assertEquals(70, pencil.getPointDurability());
    }

    @Test
    public void shouldDisallowEditingWhenPointDurabilityIsZero() {
        pencil = new GraphitePencil(29, 10, 100);
        pencil.write(paper, "An       a day keeps the doctor away");
        pencil.edit(paper, 3, "onion");
        assertEquals("An onio  a day keeps the doctor away", paper.toString());
    }

    @Test
    public void shouldAllowEditingInWhitespace() {
        pencil.write(paper, "She sells sea shells down by the sea shore");
        pencil.edit(paper, 20, "\n");
        assertEquals("She sells sea shells\ndown by the sea shore", paper.toString());
    }
}
