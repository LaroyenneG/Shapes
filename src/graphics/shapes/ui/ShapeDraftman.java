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

        ColorAttributes colorAttributes = (ColorAttributes) rectangle.getAttributes(ColorAttributes.ID);
        Rectangle rect = rectangle.getRect();

        if (colorAttributes == null) {
            colorAttributes = DEFAULT_COLOR_ATTRIBUTES;
        }

        if (colorAttributes.filled) {
            graph2D.setColor(colorAttributes.filledColor);
            graph2D.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
        if (colorAttributes.stroked) {
            graph2D.setColor(colorAttributes.strokedColor);
            graph2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        }

    }

    @Override
    public void visitCircle(SCircle circle) {
        ColorAttributes colorAttributes = (ColorAttributes) circle.getAttributes(ColorAttributes.ID);


    }

    @Override
    public void visitCollection(SCollection collection) {

        Iterator<Shape> shapes = collection.iterator();
        while (shapes.hasNext()) {
            shapes.next().accept(this);
        }

    }

    @Override
    public void visitText(SText text) {

    }
}
