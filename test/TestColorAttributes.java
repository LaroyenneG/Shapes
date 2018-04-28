import graphics.shapes.attributes.ColorAttributes;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestColorAttributes {

    private static final Random random = new Random();


    @Test
    public void testConstructor() {


        for (int i = 0; i < 1000; i++) {

            Color color1 = new Color(Math.abs(random.nextInt() % 256), Math.abs(random.nextInt() % 256), Math.abs(random.nextInt() % 256));

            Color color2 = new Color(Math.abs(random.nextInt() % 256), Math.abs(random.nextInt() % 256), Math.abs(random.nextInt() % 256));

            boolean b1 = random.nextBoolean();

            boolean b2 = random.nextBoolean();

            ColorAttributes colorAttributes = new ColorAttributes(b1, b2, color1, color2);

            assertEquals(b1, colorAttributes.filled);
            assertEquals(b2, colorAttributes.stroked);
            assertEquals(color1, colorAttributes.filledColor);
            assertEquals(color2, colorAttributes.strokedColor);


            /* Tester id */
        }
    }
}
