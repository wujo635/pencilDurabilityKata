import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphitePencilTest {

    private GraphitePencil pencil = new GraphitePencil();

    @Test
    public void shouldWriteToPaper() {
        pencil.write("Hello");
        assertEquals("Hello", pencil.paper.toString());
    }

}
