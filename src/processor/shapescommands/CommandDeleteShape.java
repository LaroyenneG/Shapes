package processor.shapescommands;

import processor.engine.Processor;

public class CommandDeleteShape extends CommandShapesEditor {

    public CommandDeleteShape() {
        super("delete");
    }

    /*
    supprime la figure
     */

    @Override
    public void execute(Processor processor) {

    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(" ");
        buffer.append("<id>");

        return new String(buffer);
    }
}
