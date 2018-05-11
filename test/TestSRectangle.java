import graphics.shapes.SRectangle;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestSRectangle {


    @Test
    public void testConstructor() {
        Point point = new Point(2,4);
        SRectangle rec = new SRectangle(point,4,2 );
        assertEquals(point, rec.getLoc());

    }

    @Test
    public void testGetBounds(){
        Point point = new Point(2,4);
        SRectangle rec = new SRectangle(point, 4,2);
        assertEquals(new Rectangle(2,4,4,2), rec.getBounds());
    }

}
