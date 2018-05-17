package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Command;
import processor.engine.Processor;

public class CommandCreateShape extends Command {

    public CommandCreateShape() {
        super("create");
    }

    /*
    cree une nouvelle figure
     */

    private Shape createShape(String name) throws CommandShapesExecption {

        Shape shape = null;

        switch (name) {

            case "rectangle":

                break;

            default:
                throw new CommandShapesExecption("invalid shape name");
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
