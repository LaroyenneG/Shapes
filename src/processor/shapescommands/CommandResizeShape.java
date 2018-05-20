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
            Shape shape = selectShape(processor);
            resizeShape(shape, readInt(processor));
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
