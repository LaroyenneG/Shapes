package processor.shapescommands;

import graphics.shapes.Shape;
import processor.engine.Processor;

public class CommandExportModel extends CommandShapesEditor {

    private int dictionnary;

    public CommandExportModel() {
        super("export");
    }


    private static String buildAttributesCommand(Shape shape, String varName) {

        String command = "";


        return command;
    }

    //Map<String, String>

    @Override
    public void execute(Processor processor, String[] args) {

        dictionnary = 0;

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
