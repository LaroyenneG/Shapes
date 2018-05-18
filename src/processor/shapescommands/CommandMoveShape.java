package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandMoveShape extends CommandShapesEditor {

    public CommandMoveShape() {
        super("move");
    }


    @Override
    public void execute(Processor processor) {

        try {

            Shape shape = commandSelectShape(processor);

            shape.setLoc(readPoint(processor));

        } catch (CommandShapesException e) {
            processor.out().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(" ");
        buffer.append("<id> <position>");

        return new String(buffer);
    }
}
