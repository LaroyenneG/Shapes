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

        Rectangle rec = new Rectangle();// A modifier
        return rec;
    }

    public void accept(ShapeVisitor sv) {

    }
}
