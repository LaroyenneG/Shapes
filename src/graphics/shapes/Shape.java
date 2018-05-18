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

    public abstract Point getLoc();

    public abstract void setLoc(Point loc);

    public abstract void translate(int dx, int dy);

    public abstract Rectangle getBounds();

    public abstract void accept(ShapeVisitor sv);

    public String toString() {

        StringBuffer string = new StringBuffer();
        String className = getClass().getSimpleName();
        className = className.substring(1);
        string.append(className);
        string.append("@");
        string.append(hashCode());
        string.append("\tposition:");
        string.append(getLoc().x);
        string.append(",");
        string.append(getLoc().y);

        return new String(string);
    }
}
