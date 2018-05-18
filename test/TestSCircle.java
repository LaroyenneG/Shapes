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


    @Test
    public void testGetBounds() {

        Point point = new Point(1, 2);

        SCircle circle = new SCircle(point, 2);


        Rectangle bounds = new Rectangle(1, 2, 4, 4);

        assertEquals(bounds, circle.getBounds());
    }
}
