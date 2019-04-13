import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphitePencilTest {

    private GraphitePencil pencil = new GraphitePencil(100);
    private StringBuilder paper = new StringBuilder();

    @Test
    public void shouldHavePointDurabilityOfZeroByDefault() {
        pencil = new GraphitePencil();
        assertEquals(0, pencil.getPointDurability());
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
        pencil = new GraphitePencil(3);
        pencil.write(paper, "Text");
        assertEquals("Tex", paper.toString());
    }
}
