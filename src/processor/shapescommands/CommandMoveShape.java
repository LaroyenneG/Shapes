package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandMoveShape extends CommandShapesEditor {

    public CommandMoveShape() {
        super("move");
    }


    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length != 4) {
                throw new CommandShapesException("invalid argument number");
            }

            Shape shape = selectShape(processor, args[0]);

            shape.setLoc(readPoint(args[1], args[2]));

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
