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

        loc.x += dx;
        loc.y += dy;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(loc.x, loc.y, (int) rect.getWidth(), (int) rect.getHeight());
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitRectangle(this);
    }

    @Override
    public void reSize(int dx, int dy) {
        rect.width += dx;
        rect.height += dy;
    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();

        string.append(super.toString());
        string.append("\twidth : ");
        string.append(rect.width);
        string.append("\theight : ");
        string.append(rect.height);

        return new String(string);
    }

}
