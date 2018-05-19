package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandDeleteShape extends CommandShapesEditor {

    public CommandDeleteShape() {
        super("delete");
    }

    @Override
    public void execute(Processor processor) {

        try {

            Shape shape = selectShape(processor);

            model(processor).deleteShape(shape);

        } catch (CommandShapesException e) {
            processor.out().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(' ');
        buffer.append("[id]");

        return new String(buffer);
    }
}
