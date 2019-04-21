import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphitePencilTest {

    private GraphitePencil pencil = new GraphitePencil(100, 10, 100);
    private StringBuilder paper = new StringBuilder();

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
}
