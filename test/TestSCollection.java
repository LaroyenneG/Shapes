import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSCollection {


    private static SCollection toolBuild() {


        SCollection sCollection = new SCollection();
        sCollection.add(new SCircle(new Point(0, 1), 1));
        sCollection.add(new SRectangle(new Point(3, 1), 2, 2));

        return sCollection;
    }


    @Test
    public void testGetBounds() {

        SCollection collection = toolBuild();
        assertEquals(new Rectangle(0, 1, 5, 2), collection.getBounds());
    }


    @Test
    public void testGetLocation() {

        SCollection collection = toolBuild();

    }

    @Test
    public void testTranslate() {
        SCollection collection = toolBuild();

    }

    @Test
    public void testSetLocation() {
        SCollection collection = toolBuild();

    }


}
