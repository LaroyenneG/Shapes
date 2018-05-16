package graphics.shapes.attributes;

import java.awt.*;

public class FontAttributes extends Attributes {

    public static final String ID = "font";

    public Font font;
    public Color fontColor;

    public FontAttributes(Font font, Color fontColor) {
        this.font = font;
        this.fontColor = fontColor;
    }

    public FontAttributes() {
        this(null, null);
    }

    @Override
    public String getId() {
        return ID;
    }

    /*
    Retourne le rectangle entourant la chaine de carractére.
    Voir avec les outils java. Composant de la plateforme java.
     */
    public Rectangle getBounds(String str) {

        return null;
    }
}
