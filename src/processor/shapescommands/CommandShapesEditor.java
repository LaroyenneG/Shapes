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

    static Shape commandSelectShape(Processor processor) throws CommandShapesException {

        int id = processor.scanner().nextInt();

        return searchShape(model(processor), id);
    }

    static Point readPoint(Processor processor) {

        int x = processor.scanner().nextInt();
        int y = processor.scanner().nextInt();

        return new Point(x, y);
    }

    static int readInt(Processor processor) {

        return processor.scanner().nextInt();
    }

    static String readString(Processor processor) {

        return processor.scanner().next();
    }

    @Override
    public abstract void execute(Processor processor);
}
