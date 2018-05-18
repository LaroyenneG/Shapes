package graphics.shapes.attributes;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
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
        this(new Font("Courier New", 1, 14), Color.BLACK);
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
        Font font = Font.decode("Courier New-" + 16);

        BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setFont(font);
        FontRenderContext render = image.createGraphics().getFontRenderContext();
        GlyphVector v = font.createGlyphVector(render, "sdfghjk");

        int hauteur, largeur;
        hauteur = v.getPixelBounds(render, 0, 0).height;
        largeur = v.getPixelBounds(render, 0, 0).width;

        return new Rectangle(0, 0, hauteur, largeur);
    }

}
