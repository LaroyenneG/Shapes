package graphics.shapes.attributes;

import java.awt.*;

public class ColorAttributes extends Attributes{

    private String id;
    public boolean filled;
    public boolean stroked;
    public Color filledColor;
    public Color strokedColor;

    public ColorAttributes(boolean filled, boolean stroked, Color filledColor, Color strokedColor) {
        this.filled = filled;
        this.stroked = stroked;
        this.filledColor = filledColor;
        this.strokedColor = strokedColor;
    }

    public String getId()
    {
        return id;
    }
}
