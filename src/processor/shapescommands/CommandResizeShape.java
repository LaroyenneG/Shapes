package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandResizeShape extends CommandShapesEditor {

    public CommandResizeShape() {
        super("resize");
    }

    private static void resizeShape(Shape shape, int r) {

        /*
        methode manquante pour cet op
         */
    }

    @Override
    public void execute(Processor processor) {

        try {
            Shape shape = commandSelectShape(processor);
            resizeShape(shape, readInt(processor));
        } catch (CommandShapesException e) {
            processor.out().println(e.getMessage());
        }

    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(" ");
        buffer.append("<id> <size>");

        return new String(buffer);
    }
}
