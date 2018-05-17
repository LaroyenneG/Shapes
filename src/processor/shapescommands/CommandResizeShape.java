package processor.shapescommands;

import processor.engine.Processor;

public class CommandResizeShape extends CommandShapesEditor {

    public CommandResizeShape() {
        super("resize");
    }

    /*
    redimentionne la figure.
     */

    @Override
    public void execute(Processor processor) {

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
