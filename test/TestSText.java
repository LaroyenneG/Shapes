import graphics.shapes.SText;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSText {

    @Test
    public void testGetBounds() {

        Point point = new Point(500, 500);

        SText text = new SText(point, "toto");

        assertEquals(point, text.getBounds().getLocation());
    }
}
