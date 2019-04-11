import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphitePencilTest {

    private GraphitePencil pencil = new GraphitePencil();
    private StringBuilder paper = new StringBuilder();

    @Test
    public void shouldWriteOneWordToPaper() {
        pencil.write(paper, "Hello");
        assertEquals("Hello", paper.toString());
    }
}
