package com.pillar.kata;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WritingTests {

    private GraphitePencil pencil = new GraphitePencil(100, 10, 100);
    private StringBuilder paper = new StringBuilder();

    @Test
    public void shouldWriteOneWordToPaper() {
        pencil.write(paper, "Hello");
        assertEquals("Hello", paper.toString());
    }

    @Test
    public void shouldWritePhraseToPaper() {
        pencil.write(paper, "Multiple words");
        assertEquals("Multiple words", paper.toString());
    }

    @Test
    public void shouldAppendMultipleWritesToPaper() {
        pencil.write(paper, "She sells sea shells");
        pencil.write(paper, " down by the sea shore");
        assertEquals("She sells sea shells down by the sea shore", paper.toString());
    }
}
