package graphics.shapes;

import java.awt.*;

public class SText extends Shape {

    private String text;
    private Point loc;

    public SText(Point loc, String text) {

        this.loc = loc;
        this.text = text;
    }

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
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

        Rectangle rec = new Rectangle();// A modifier
        return rec;
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitText(this);
    }
}
