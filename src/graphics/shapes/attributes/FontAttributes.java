package graphics.shapes.attributes;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class FontAttributes extends Attributes {

    public static final String ID = "font";
    private static final String DEFAULT_FONT = "Courier New";
    private static final int DEFAULT_SIZE = 14;
    private static final Color DEFAULT_COLOR = Color.BLACK;

    public Font font;
    public Color fontColor;

    public FontAttributes(Font font, Color fontColor) {
        this.font = font;
        this.fontColor = fontColor;
    }

    public FontAttributes() {
        this(new Font(DEFAULT_FONT, Font.BOLD, DEFAULT_SIZE), DEFAULT_COLOR);
    }

    @Override
    public String getId() {
        return ID;
    }


    public Rectangle getBounds(String str) {

        return font.getStringBounds(str, new FontRenderContext(new AffineTransform(), true, true)).getBounds();
    }

    public void setFontSize(int size) {
        font = new Font(font.getName(), font.getStyle(), size);
    }


}
