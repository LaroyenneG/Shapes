package processor.shapescommands;

import processor.engine.Processor;

public class CommandExportModel extends CommandShapesEditor {

    public CommandExportModel() {
        super("export");
    }


    @Override
    public void execute(Processor processor, String[] args) {

        if (args.length != 1) {
            try {
                throw new CommandShapesException("invalid argument");
            } catch (CommandShapesException e) {
                processor.err().println(e.getMessage());
            }
        }


    }

    @Override
    public String toString() {

        StringBuffer string = new StringBuffer();

        string.append(super.toString());
        string.append(' ');
        string.append("<file>");

        return new String(string);
    }
}
