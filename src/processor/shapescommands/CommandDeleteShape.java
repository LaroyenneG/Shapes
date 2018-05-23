package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandDeleteShape extends CommandShapesEditor {

    public CommandDeleteShape() {
        super("delete");
    }

    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length != 1) {
                throw new CommandShapesException("invalid argument number");
            }

            Shape shape = selectShape(processor, args[0]);

            model(processor).deleteShape(shape);

        } catch (CommandShapesException e) {
            processor.err().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(' ');
        buffer.append("<id>");

        return new String(buffer);
    }
}
