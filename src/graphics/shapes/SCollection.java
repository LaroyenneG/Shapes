package graphics.shapes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SCollection extends Shape {

    private List<Shape> shapes;

    public SCollection() {
        shapes = new ArrayList<>();
    }

    public void add(Shape shape) {
        shapes.add(shape);
    }

    public Iterator iterator() {
        return shapes.iterator();
    }

    @Override
    public Point getLoc() {

        return null;
    }

    @Override
    public void setLoc(Point loc) {

    }

    @Override
    public void translate(int dx, int dy) {

    }

    @Override
    public Rectangle getBounds() {

        Rectangle rectangle = new Rectangle();

        for (Shape s : shapes) {
            rectangle = rectangle.union(s.getBounds());
        }

        return rectangle;
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitCollection(this);
    }
}
