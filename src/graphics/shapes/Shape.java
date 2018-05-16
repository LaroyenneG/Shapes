package graphics.shapes;

import graphics.shapes.attributes.Attributes;

import java.awt.*;
import java.util.TreeMap;

public abstract class Shape {

    TreeMap<String, Attributes> attributes;

    public Shape() {
        attributes = new TreeMap<>();
    }

    public void addAttributes(Attributes attr) {
        attributes.put(attr.getId(), attr);
    }

    public Attributes getAttributes(String attr) {

        return attributes.get(attr);
    }

    /*
    @Override
    public abstract String toString();
    */

    public abstract Point getLoc();

    public abstract void setLoc(Point loc);

    public abstract void translate(int dx, int dy);

    public abstract Rectangle getBounds();

    public abstract void accept(ShapeVisitor sv);
}
