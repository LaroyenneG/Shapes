package graphics.shapes;

import graphics.shapes.attributes.FontAttributes;

import java.awt.*;

public class SText extends Shape {

    private String text;
    private Point loc;

    public SText(Point loc, String text) {

        this.loc = loc;
        this.text = text;
        addAttributes(new FontAttributes());
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

        FontAttributes font = (FontAttributes) getAttributes(FontAttributes.ID);

        Rectangle bounds = font.getBounds(text);

        bounds.setLocation(new Point(loc.x, loc.y));

        return bounds;
    }

    @Override
    public void accept(ShapeVisitor sv) {
        sv.visitText(this);
    }

    @Override
    public void reSize(int dx, int dy) {

        FontAttributes font = (FontAttributes) getAttributes(FontAttributes.ID);

        int oldSize = font.font.getSize();

        int size = oldSize + Math.abs(dx) < dy ? Math.abs(dx) : dy;

        font.setFontSize(size);

        if (getBounds().height < MINIMAL_SIZE || getBounds().width < MINIMAL_SIZE) {
            font.setFontSize(oldSize);
        }
    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();
        string.append(super.toString());
        string.append("\ttext : ");
        string.append(text);

        return new String(string);
    }

}
