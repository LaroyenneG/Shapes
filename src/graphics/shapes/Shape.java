package graphics.shapes;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.SelectionAttributes;

import java.awt.*;
import java.util.TreeMap;

public abstract class Shape {

    public static final int MINIMAL_SIZE = 15;

    TreeMap<String, Attributes> attributes;

    public Shape() {
        attributes = new TreeMap<>();
        addAttributes(new SelectionAttributes());
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

    public abstract void reSize(int x, int y);

    public String toString() {

        StringBuffer string = new StringBuffer();
        String className = getClass().getSimpleName();
        className = className.substring(1);
        string.append(className);
        string.append("@");
        string.append(hashCode());

        for (int i = className.length() + String.valueOf(hashCode()).length(); i < 23; i++) {
            string.append(' ');
        }

        string.append("position : ");
        string.append('(');
        string.append(getLoc().x);
        string.append(",");
        string.append(getLoc().y);
        string.append(')');

        return new String(string);
    }
}
