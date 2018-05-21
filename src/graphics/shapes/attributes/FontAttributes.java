package graphics.shapes.attributes;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FontAttributes extends Attributes {

    public static final String ID = "font";

    public Font font;
    public Color fontColor;

    public FontAttributes(Font font, Color fontColor) {
        this.font = font;
        this.fontColor = fontColor;
    }

    public FontAttributes() {
        this(new Font("Courier New", Font.BOLD, 14), Color.BLACK);
    }

    @Override
    public String getId() {
        return ID;
    }


    public Rectangle getBounds(String str) {

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        BufferedImage image = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = (Graphics2D) image.getGraphics();
        g.setFont(font);

        return font.getStringBounds(str, g.getFontRenderContext()).getBounds();
    }

}
