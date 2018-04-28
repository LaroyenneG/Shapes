import graphics.shapes.SCircle;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSCircle {

    @Test
    public void testConstructor() {

        Point point = new Point(1, 6);
        SCircle sCircle = new SCircle(point, 5);

        assertEquals(point, sCircle.getLoc());
        assertEquals(5, sCircle.getRadius());
    }
}
