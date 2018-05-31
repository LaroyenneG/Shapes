package graphics.shapes.ui;

import graphics.shapes.*;
import graphics.shapes.Shape;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Iterator;

public class ShapeDraftman implements ShapeVisitor {


    public static final int HANDLER_SIZE = 8;

    public static final ColorAttributes DEFAULT_COLOR_ATTRIBUTES = new ColorAttributes(false, true, Color.WHITE, Color.BLACK);
    private static final Color HANDLER_COLOR = Color.GRAY;

    private Graphics2D graph2D;

    public ShapeDraftman(Graphics g) {
        graph2D = (Graphics2D) g;
    }

    private void drawHandler(Shape shape) {


        Rectangle r = shape.getBounds();
        graph2D.setColor(HANDLER_COLOR);

        graph2D.drawRect(shape.getLoc().x, shape.getLoc().y, HANDLER_SIZE, HANDLER_SIZE);
        graph2D.drawRect(shape.getLoc().x + r.width - HANDLER_SIZE, shape.getLoc().y + r.height - HANDLER_SIZE, HANDLER_SIZE, HANDLER_SIZE);
    }


    @Override
    public void visitRectangle(SRectangle rectangle) {

        ColorAttributes colorAttributes = (ColorAttributes) rectangle.getAttributes(ColorAttributes.ID);
        Rectangle rect = rectangle.getBounds();

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


        graph2D.drawRect(rectangle.getBounds().x, rectangle.getBounds().y, rectangle.getBounds().width, rectangle.getBounds().height);

        graph2D.drawRect(rect.x, rect.y, rect.width, rect.height);
        SelectionAttributes sa = (SelectionAttributes) rectangle.getAttributes(SelectionAttributes.ID);
        if (sa.isSelected()) {
            drawHandler(rectangle);
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

        SelectionAttributes sa = (SelectionAttributes) circle.getAttributes(SelectionAttributes.ID);
        if (sa.isSelected()) {
            drawHandler(circle);
        }
    }

    @Override
    public void visitCollection(SCollection collection) {


        Iterator<Shape> shapes = collection.iterator();
        while (shapes.hasNext()) {
            shapes.next().accept(this);
        }

        SelectionAttributes sa = (SelectionAttributes) collection.getAttributes(SelectionAttributes.ID);
        if (sa.isSelected()) {
            drawHandler(collection);
        }

    }

    @Override
    public void visitText(SText text) {

        ColorAttributes colorAttributes = (ColorAttributes) text.getAttributes(ColorAttributes.ID);
        FontAttributes fontAttributes = (FontAttributes) text.getAttributes(FontAttributes.ID);
        Rectangle bounds = text.getBounds();

        if (colorAttributes == null) {
            colorAttributes = DEFAULT_COLOR_ATTRIBUTES;
        }
        if (colorAttributes.filled) {
            graph2D.setColor(colorAttributes.filledColor);
            graph2D.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        if (colorAttributes.stroked) {
            AttributedString attributedString = new AttributedString(text.getText());
            attributedString.addAttribute(TextAttribute.FONT, fontAttributes.font);
            attributedString.addAttribute(TextAttribute.FOREGROUND, colorAttributes.strokedColor);
            graph2D.drawString(attributedString.getIterator(), text.getLoc().x, text.getLoc().y + bounds.height);
        }

        SelectionAttributes sa = (SelectionAttributes) text.getAttributes(SelectionAttributes.ID);
        if (sa.isSelected()) {
            drawHandler(text);
        }
    }
}
