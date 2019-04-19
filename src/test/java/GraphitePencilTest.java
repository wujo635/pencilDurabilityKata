import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphitePencilTest {

    private GraphitePencil pencil = new GraphitePencil(100, 10);
    private StringBuilder paper = new StringBuilder();

    @Test
    public void shouldHavePointDurabilityOfZeroByDefault() {
        pencil = new GraphitePencil();
        assertEquals(0, pencil.getPointDurability());
    }

    @Test
    public void shouldHaveInitialLengthValueOf10() {
        pencil = new GraphitePencil();
        assertEquals(10, pencil.getLength());
    }

    @Test
    public void shouldDisallowNegativeInitialPointDurabilityValue() {
        pencil = new GraphitePencil(-1, 10);
        assertEquals(0, pencil.getPointDurability());
    }

    @Test
    public void shouldDisallowNegativeInitialLengthValue() {
        pencil = new GraphitePencil(100, -1);
        assertEquals(0, pencil.getLength());
    }

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

    @Test
    public void shouldAllowPointDurabilitySettingOnCreation() {
        assertEquals(100, pencil.getPointDurability());
    }

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
        pencil = new GraphitePencil(4, 10);
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
        pencil = new GraphitePencil(100, 0);
        pencil.sharpen();
        assertEquals(0, pencil.getLength());
    }

    @Test
    public void shouldDisallowSharpenToResetPointDurabilityWhenLengthIsZero() {
        pencil = new GraphitePencil(100, 0);
        pencil.write(paper, "Text");
        pencil.sharpen();
        assertEquals(95, pencil.getPointDurability());
    }

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
}
