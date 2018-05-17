package processor.shapescommands;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import processor.engine.Processor;

import java.util.Iterator;

public class CommandListShapes extends CommandShapesEditor {

    public CommandListShapes() {
        super("list");
    }

    /*
    liste toute les shapes en UTILISANT LE METHODE toString().
     */

    @Override
    public void execute(Processor processor) {

        SCollection shapes = model(processor);

        Iterator<Shape> iterator = shapes.iterator();

        while (iterator.hasNext()) {
            processor.out().print("\t-> ");
            processor.out().println(iterator.next());
        }
    }
}
