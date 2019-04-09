import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GraphitePencilTest {

    private GraphitePencil pencil = new GraphitePencil();

    @Test
    public void sanityCheck() {
        assertEquals(1, pencil.sanityCheck());
    }

}
