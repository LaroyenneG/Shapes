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

    public Iterator<Shape> iterator() {
        return shapes.iterator();
    }

    public void deleteAllShapes() {

        shapes.clear();
    }

    public void deleteShape(Shape shape) {

        shapes.remove(shape);
    }

    @Override
    public Point getLoc() {

        return getBounds().getLocation();
    }

    @Override
    public void setLoc(Point loc) {

        Iterator<Shape> shapes = this.iterator();
        while (shapes.hasNext()) {
            shapes.next().setLoc(loc);
        }
    }

    @Override
    public void translate(int dx, int dy) {

        Iterator<Shape> shapes = this.iterator();
        while (shapes.hasNext()) {
            shapes.next().translate(dx, dy);
        }
    }

    @Override
    public Rectangle getBounds() {

        Rectangle rectangle = null;

        for (Shape s : shapes) {
            if (rectangle == null) {
                rectangle = s.getBounds();
            } else {
                rectangle = rectangle.union(s.getBounds());
            }
        }

        return rectangle;
    }

    @Override
    public void accept(ShapeVisitor sv) {

        sv.visitCollection(this);
    }

    @Override
    public void reSize(int dx, int dy) {

        Iterator<Shape> shapes = iterator();
        while (shapes.hasNext()) {
            shapes.next().reSize(dx, dy);
        }
    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();
        string.append(super.toString());

        return new String(string);
    }
}
