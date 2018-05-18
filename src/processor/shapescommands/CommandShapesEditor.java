package processor.shapescommands;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import processor.engine.Command;
import processor.engine.Processor;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Iterator;

public abstract class CommandShapesEditor extends Command {

    public CommandShapesEditor(String name) {
        super(name);
    }

    static SCollection model(Processor processor) {
        return (SCollection) processor.getSystem();
    }

    private static Shape searchShape(SCollection collection, int id) throws CommandShapesExecption {

        Shape shape = null;

        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {

            Object selected = iterator.next().hashCode();

            if (selected.hashCode() == id) {
                shape = (Shape) selected;
                break;
            }
        }

        if (shape == null) {
            throw new CommandShapesExecption("invalid shape id");
        }

        return shape;
    }

    protected static Shape commandSelectShape(Processor processor) throws InputMismatchException, CommandShapesExecption {

        int id = processor.scanner().nextInt();

        return searchShape(model(processor), id);
    }

    protected static Point readPoint(Processor processor) throws InputMismatchException {

        int x = processor.scanner().nextInt();
        int y = processor.scanner().nextInt();

        return new Point(x, y);
    }

    protected static int readInt(Processor processor) throws InputMismatchException {

        return processor.scanner().nextInt();
    }

    @Override
    public abstract void execute(Processor processor);
}
