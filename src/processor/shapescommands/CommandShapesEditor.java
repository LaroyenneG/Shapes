package processor.shapescommands;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import processor.engine.Command;
import processor.engine.Processor;

import java.awt.*;
import java.util.Iterator;

public abstract class CommandShapesEditor extends Command {

    public CommandShapesEditor(String name) {
        super(name);
    }

    static SCollection model(Processor processor) {
        return (SCollection) processor.getSystem();
    }

    private static Shape searchShape(SCollection collection, int id) throws CommandShapesException {

        Shape shape = null;

        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {

            Shape selected = iterator.next();

            if (selected.hashCode() == id) {
                shape = selected;
                break;
            }
        }

        if (shape == null) {
            throw new CommandShapesException("invalid shape id");
        }

        return shape;
    }

    static Shape selectShape(Processor processor, String string) throws CommandShapesException {

        int id = readInt(string);

        return searchShape(model(processor), id);
    }

    static Point readPoint(String a, String b) {

        return new Point(readInt(a), readInt(b));
    }

    static int readInt(String string) {

        return Integer.parseInt(string);
    }

    static boolean readBool(String string) {
        return Boolean.parseBoolean(string);
    }

    static String convertColor(Color color) {

        String hex = Integer.toHexString(color.getRGB());

        hex = "#" + hex.substring(2, hex.length());

        return hex;
    }

    static String convertFont(Font font) {
        return font.getName() + ":" + font.getStyle() + ":" + font.getSize();
    }

    static Color readColor(String string) {
        return Color.decode(string);
    }

    static Font readFont(String string) {

        String[] parameters = string.split(":");

        Font font = new Font(parameters[0], Integer.parseInt(parameters[1]), Integer.parseInt(parameters[2]));

        return font;
    }

    @Override
    public abstract void execute(Processor processor, String[] args);
}
