package processor.shapescommands;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import processor.engine.Processor;

import java.util.Iterator;

public class CommandListShapes extends CommandShapesEditor {

    public CommandListShapes() {
        super("list");
    }

    private static void printList(Processor processor, SCollection collection, int i) {

        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {

            Shape shape = iterator.next();

            for (int n = 0; n < i * 2; n++) {
                processor.out().print('\t');
            }

            processor.out().println("-> " + shape);

            if (shape instanceof SCollection) {
                printList(processor, (SCollection) shape, i + 1);
            }
        }
    }

    @Override
    public void execute(Processor processor, String[] args) {

        SCollection shapes = model(processor);

        printList(processor, shapes, 0);
    }
}
