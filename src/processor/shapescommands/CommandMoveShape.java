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

            Shape shape = selectShape(processor);

            shape.setLoc(readPoint(processor));

        } catch (CommandShapesException e) {
            processor.err().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();

        string.append(super.toString());
        string.append(' ');
        string.append("<id> <point>");

        return new String(string);
    }
}
