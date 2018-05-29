package processor.shapescommands;

import processor.engine.Processor;

public class CommandClearShape extends CommandShapesEditor {

    public CommandClearShape() {
        super("clear");
    }

    @Override
    public void execute(Processor processor, String[] args) {

        try {
            if (args.length != 0) {
                throw new CommandShapesException("invalid argument number");
            }

            model(processor).deleteAllShapes();

        } catch (CommandShapesException e) {
            processor.out().println(e.getMessage());
        }
    }
}
