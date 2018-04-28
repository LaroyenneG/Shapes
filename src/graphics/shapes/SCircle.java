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

        this.radius = radius;
    }

    public Point getLoc() {

        return loc;
    }

    public void setLoc(Point loc) {

        this.loc = loc;
    }

    public void translate(int dx, int dy) {

        loc.translate(dx, dy);
    }

    public Rectangle getBounds() {

        Rectangle rec = new Rectangle();// à modifier
        return rec;
    }

    public void accept(ShapeVisitor sv) {

    }
}
