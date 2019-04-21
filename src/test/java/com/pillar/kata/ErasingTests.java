package com.pillar.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ErasingTests {

    private GraphitePencil pencil = new GraphitePencil(100, 10, 100);
    private StringBuilder paper = new StringBuilder();

    @Test
    public void shouldEraseSubstringWhenPaperContainsSubstring() {
        pencil.write(paper, "Text");
        pencil.erase(paper, "Text");
        assertEquals("    ", paper.toString());
    }

    @Test
    public void shouldEraseLastOccurrenceOfSubstringWhenPaperContainsSubstring() {
        pencil.write(paper, "How much wood would a woodchuck chuck if a woodchuck could chuck wood?");
        pencil.erase(paper, "chuck");
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could       wood?", paper.toString());
    }

    @Test
    public void shouldDisallowEraseWhenPaperDoesNotContainSubstring() {
        pencil.write(paper, "Text");
        pencil.erase(paper, "chuck");
        assertEquals("Text", paper.toString());
    }

    @Test
    public void shouldAllowMultipleErasesWhenPaperContainsSubstringMultipleTimes() {
        pencil.write(paper, "How much wood would a woodchuck chuck if a woodchuck could chuck wood?");
        pencil.erase(paper, "chuck");
        pencil.erase(paper, "chuck");
        assertEquals("How much wood would a woodchuck chuck if a wood      could       wood?", paper.toString());
    }

    @Test
    public void shouldEraseStringsContainingWhitespace() {
        pencil.write(paper, "How much wood would a woodchuck chuck if a woodchuck could chuck wood?");
        pencil.erase(paper, "could chuck");
        assertEquals("How much wood would a woodchuck chuck if a woodchuck             wood?", paper.toString());
    }

    @Test
    public void shouldDecreaseEraserDurabilityByOnePerCharacterErased() {
        pencil.write(paper, "Text");
        pencil.erase(paper, "x");
        assertEquals(99, pencil.getEraserDurability());
    }

    @Test
    public void shouldDisallowEraseAfterEraserDurabilityReachesZero() {
        pencil = new GraphitePencil(100, 10, 3);
        pencil.write(paper, "Buffalo Bill");
        pencil.erase(paper, "Bill");
        assertEquals("Buffalo B   ", paper.toString());
    }

    @Test
    public void shouldAllowErasingSpaceWithoutDecreasingEraserDurability() {
        pencil.write(paper, "Buffalo Bill");
        pencil.erase(paper, " ");
        assertEquals(100, pencil.getEraserDurability());
    }

    @Test
    public void shouldAllowErasingNewlineWithoutDecreasingEraserDurability() {
        pencil.write(paper, "Buffalo\nBill");
        pencil.erase(paper, "\n");
        assertEquals(100, pencil.getEraserDurability());
    }

    @Test
    public void shouldAllowErasingStringsThatContainWhitespaceAndNonWhitespace() {
        pencil.write(paper, "Buffalo Bill");
        pencil.erase(paper, "Buffalo Bill");
        assertEquals("            ", paper.toString());
    }

}
