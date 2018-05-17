package processor.shapescommands;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import processor.engine.Command;
import processor.engine.Processor;
import processor.engine.ProcessorException;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Iterator;

public abstract class CommandShapesEditor extends Command {

    public CommandShapesEditor(String name) {
        super(name);
    }

    SCollection model(Processor processor) {
        return (SCollection) processor.getSystem();
    }

    private Shape searchShape(SCollection collection, int id) throws CommandShapesExecption {

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

    protected Shape commandSelectShape(Processor processor) throws CommandShapesExecption {

        int id = -1;

        try {

            id = processor.scanner().nextInt();

            return searchShape(model(processor), id);

        } catch (InputMismatchException e) {
            throw new CommandShapesExecption("bad input");
        }
    }

    protected Point readPoint(Processor processor) throws ProcessorException {

        try {

            int x = processor.scanner().nextInt();
            int y = processor.scanner().nextInt();

            return new Point(x, y);

        } catch (InputMismatchException e) {
            throw new ProcessorException("bad input");
        }
    }

    @Override
    public abstract void execute(Processor processor);
}
