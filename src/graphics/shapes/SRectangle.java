package graphics.shapes;

import java.awt.*;

public class SRectangle extends Shape {

    private Point loc;
    private Rectangle rect;

    public SRectangle(Point loc, int width, int height) {

        this.loc = loc;
        rect = new Rectangle(loc.x, loc.y, width, height);
    }

    public Rectangle getRect() {

        return rect;
    }

    public Point getLoc() {

        return loc;
    }

    public void setLoc(Point loc) {

        this.loc = loc;
    }

    public void translate(int dx, int dy) {

        loc.translate(dx,dy);
    }

    public Rectangle getBounds() {

        Rectangle rec = new Rectangle();
        return rec;
    }

    public void accept(ShapeVisitor sv) {

    }
}
