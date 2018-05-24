package graphics.shapes;

import java.awt.*;

public class SCircle extends Shape {

    private int radius;
    private Point loc;

    public SCircle(Point loc, int radius) {

        this.radius = radius;
        this.loc = loc;
    }

    public int getRadius() {

        return radius;
    }

    public void setRadius(int radius) {

        if (radius >= 0) {
            this.radius = radius;
        }
    }

    @Override
    public Point getLoc() {

        return loc;
    }

    @Override
    public void setLoc(Point loc) {

        this.loc = loc;
    }

    @Override
    public void translate(int dx, int dy) {

        loc.translate(dx, dy);
    }

    @Override
    public Rectangle getBounds() {

        return new Rectangle(getLoc().x, getLoc().y, getRadius(), getRadius());
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitCircle(this);
    }

    @Override
    public void reSize(int dx, int dy) {

        int size = Math.abs(dx) < Math.abs(dy) ? dx : dy;

        if (radius + size >= MINIMAL_SIZE) {
            setRadius(radius + size);
        }
    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();

        string.append(super.toString());
        string.append("\tradius : ");
        string.append(radius);

        return new String(string);
    }

}
