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

        return new Rectangle(loc.x - radius, loc.y + radius, radius * 2, radius * 2);
    }

    public void accept(ShapeVisitor sv) {

    }
}
