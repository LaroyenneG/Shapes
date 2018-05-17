package processor.shapescommands;

import processor.engine.Processor;

public class CommandMoveShape extends CommandShapesEditor {

    public CommandMoveShape() {
        super("move");
    }

    /*
    Deplace une figue.
     */

    @Override
    public void execute(Processor processor) {

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
