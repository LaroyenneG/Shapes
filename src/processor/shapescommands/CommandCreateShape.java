package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandCreateShape extends CommandShapesEditor {

    public CommandCreateShape() {
        super("create");
    }


    private static Shape createShape(String name) throws CommandShapesException {

        Shape shape = null;

        switch (name) {

            case "rectangle":

                break;

            case "cercle":

                break;

            case "text":

                break;

            case "collection":

                break;

            default:
                throw new CommandShapesException("invalid shape name");
        }


        return shape;
    }


    @Override
    public void execute(Processor processor) {

    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(" ");
        buffer.append("<type> <position>");

        return new String(buffer);
    }
}
