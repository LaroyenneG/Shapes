package graphics.shapes;

import graphics.shapes.attributes.Attributes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Shape {

    Map<String, Attributes> attributes;

    public Shape() {
        attributes = new HashMap<>();
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
}
