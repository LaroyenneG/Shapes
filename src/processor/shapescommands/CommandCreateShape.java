package processor.shapescommands;

import processor.engine.Command;
import processor.engine.Processor;

public class CommandCreateShape extends Command {

    public CommandCreateShape(String name) {
        super("create");
    }

    /*
    cree une nouvelle figure
     */

    @Override
    public void execute(Processor processor) {

    }
}
