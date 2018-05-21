package graphics.shapes.ui;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.*;
import java.util.Iterator;

public class ShapeDraftman implements ShapeVisitor {
    public static final ColorAttributes DEFAULT_COLOR_ATTRIBUTES = new ColorAttributes(false, false, Color.WHITE, Color.WHITE);
    private Graphics2D graph2D;

    public ShapeDraftman(Graphics g) {
        graph2D = (Graphics2D) g;
    }

    private void drawHandler(Rectangle rect) {
        int size = 5;
        Color HANDLER_COLOR = Color.GRAY;
        Rectangle r = rect.getBounds();
        graph2D.setColor(HANDLER_COLOR);

        graph2D.drawRect(rect.getLocation().x - size, rect.getLocation().y - size, size, size);
        graph2D.drawRect(rect.getLocation().x + r.width, rect.getLocation().y + r.height, size, size);
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

        if (colorAttributes == null) {
            colorAttributes = DEFAULT_COLOR_ATTRIBUTES;
        }
        if (colorAttributes.filled) {
            graph2D.setColor(colorAttributes.filledColor);
            graph2D.fillOval(circle.getLoc().x, circle.getLoc().y, circle.getRadius(), circle.getRadius());
        }
        if (colorAttributes.stroked) {
            graph2D.setColor(colorAttributes.strokedColor);
            graph2D.drawOval(circle.getLoc().x, circle.getLoc().y, circle.getRadius(), circle.getRadius());
        }
    }

    @Override
    public void visitCollection(SCollection collection) {

        Rectangle rect = collection.getBounds();
        Iterator<Shape> shapes = collection.iterator();
        while (shapes.hasNext()) {
            shapes.next().accept(this);
        }

        SelectionAttributes sa = (SelectionAttributes) collection.getAttributes(SelectionAttributes.ID);
        if (sa.isSelected()) {
            this.drawHandler(rect);
        }

    }

    @Override
    public void visitText(SText text) {

        ColorAttributes colorAttributes = (ColorAttributes) text.getAttributes(ColorAttributes.ID);
        Rectangle bounds = text.getBounds();

        if (colorAttributes == null) {
            colorAttributes = DEFAULT_COLOR_ATTRIBUTES;
        }
        if (colorAttributes.filled) {
            graph2D.setColor(colorAttributes.filledColor);
            graph2D.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        if (colorAttributes.stroked) {
            graph2D.setColor(colorAttributes.strokedColor);
            graph2D.drawString(text.getText(), text.getLoc().x, text.getLoc().y);
        }

    }
}
