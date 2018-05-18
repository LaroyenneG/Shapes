package graphics.shapes.ui;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;

import java.awt.*;
import java.util.Iterator;

public class ShapeDraftman implements ShapeVisitor {
    private Graphics2D graph2D;


    public static final ColorAttributes DEFAULT_COLOR_ATTRIBUTES = new ColorAttributes(false, false, Color.WHITE, Color.WHITE);

    public ShapeDraftman(Graphics g) {
        graph2D = (Graphics2D) g;
    }

    @Override
    public void visitRectangle(SRectangle rectangle) {

        ColorAttributes colorAttributesAttributes = (ColorAttributes) rectangle.getAttributes(ColorAttributes.ID);
        Rectangle rect = rectangle.getRect();

        if (colorAttributesAttributes == null) {
            colorAttributesAttributes = DEFAULT_COLOR_ATTRIBUTES;
        }
        graph2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        System.out.println("TOTO");
    }

    @Override
    public void visitCircle(SCircle circle) {

    }

    @Override
    public void visitCollection(SCollection collection) {

        Iterator<Shape> shapes = collection.iterator();
        while (shapes.hasNext()) {
            Shape sh = shapes.next();

            if (sh instanceof SRectangle) {
                visitRectangle((SRectangle) sh);
            }
        }

    }

    @Override
    public void visitText(SText text) {

    }
}
