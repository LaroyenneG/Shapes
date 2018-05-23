package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandResizeShape extends CommandShapesEditor {

    public CommandResizeShape() {
        super("resize");
    }

    private static void resizeShape(Shape shape, int r) {


    }

    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length != 2) {
                throw new CommandShapesException("invalid argument number");
            }

            Shape shape = selectShape(processor, args[0]);
            resizeShape(shape, readInt(args[1]));
        } catch (CommandShapesException e) {
            processor.err().println(e.getMessage());
        }

    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();

        string.append(super.toString());
        string.append(' ');
        string.append("<id> <size>");

        return new String(string);
    }
}
