package graphics.shapes.ui;

import graphics.shapes.*;
import graphics.shapes.attributes.ColorAttributes;

import java.awt.*;

public class ShapeDraftman implements ShapeVisitor {


    public static final ColorAttributes DEFAULT_COLOR_ATTRIBUTES = new ColorAttributes(false, false, Color.WHITE, Color.WHITE);

    public ShapeDraftman(Graphics g) {

    }

    @Override
    public void visitRectangle(SRectangle rectangle) {

    }

    @Override
    public void visitCircle(SCircle circle) {

    }

    @Override
    public void visitCollection(SCollection collection) {

    }

    @Override
    public void visitText(SText text) {

    }
}
