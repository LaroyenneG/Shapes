import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSCollection {


    private static SCollection toolBuild() {


        SCollection sCollection = new SCollection();
        sCollection.add(new SCircle(new Point(1, 2), 2));
        sCollection.add(new SRectangle(new Point(2, 4), 4, 4));

        /*
        SCollection subcollection = new SCollection();
        subcollection.add(new SRectangle(new Point(2, 1), 3, 4));
        subcollection.add(new SRectangle(new Point(2, 3), 1, 4));

        sCollection.add(subcollection);
        */
        return sCollection;
    }


    @Test
    public void testGetBounds() {

        SCollection collection = toolBuild();
        assertEquals(new Rectangle(1,2,5,6), collection.getBounds());
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
