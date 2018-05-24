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

        SCircle circle = new SCircle(new Point(1, 2), 2);

        Rectangle bounds = new Rectangle(1, 2, 2, 2);

        assertEquals(bounds, circle.getBounds());
    }

    @Test
    public void testResize() {

        SCircle circle = new SCircle(new Point(1, 2), 60);

        circle.reSize(-50, -2);

        assertEquals(58, circle.getRadius());

        circle.reSize(-50, -60);

        assertEquals(58, circle.getRadius());
    }
}
