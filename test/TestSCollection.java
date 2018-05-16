import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class TestSCollection {


    private static SCollection toolBuild() {


        SCollection sCollection = new SCollection();
        sCollection.add(new SCircle(new Point(1, 2), 3));
        sCollection.add(new SRectangle(new Point(4, 4), 3, 6));


        SCollection subcollection = new SCollection();
        subcollection.add(new SRectangle(new Point(2, 1), 3, 4));
        subcollection.add(new SRectangle(new Point(2, 3), 1, 4));

        sCollection.add(subcollection);

        return sCollection;
    }


    @Test
    public void testGetBounds() {

        SCollection collection = toolBuild();

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
