package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

import java.awt.*;

public class CommandResizeShape extends CommandShapesEditor {

    public CommandResizeShape() {
        super("resize");
    }

    private static void resizeShape(Shape shape, Point point) {

        shape.reSize(shape.getLoc().x + point.x, shape.getLoc().y + point.y);
    }

    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length != 3) {
                throw new CommandShapesException("invalid argument number");
            }

            Shape shape = selectShape(processor, args[0]);
            resizeShape(shape, readPoint(args[1], args[2]));
        } catch (CommandShapesException e) {
            processor.err().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();

        string.append(super.toString());
        string.append(' ');
        string.append("<id> <size x> <size y>");

        return new String(string);
    }
}
