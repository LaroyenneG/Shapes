package graphics.shapes;

import graphics.shapes.attributes.Attributes;

import java.awt.*;

public abstract class Shape {


    public void addAttributes(Attributes attr) {

    }

    public Attributes getAttributes(String attr) {

        return null;
    }

    public void add(Object r) {

    }

    public abstract Point getLoc();

    public abstract void setLoc(Point loc);

    public abstract void translate(int dx, int dy);

    public abstract Rectangle getBounds();

    public abstract void accept(ShapeVisitor sv);
}
