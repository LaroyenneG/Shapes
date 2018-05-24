package processor.shapescommands;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import processor.engine.Processor;

public class CommandAddAttribute extends CommandShapesEditor {

    public static final String FONT_OPTION_NAME = "font";
    public static final String COLOR_OPTION_NAME = "color";
    public static final String SELECT_OPTION_NAME = "select";

    public CommandAddAttribute() {
        super("addatt");
    }


    private static Attributes createAttribute(String[] arg) throws CommandShapesException {

        Attributes attributes = null;

        switch (arg[0]) {

            case FONT_OPTION_NAME:
                attributes = new FontAttributes(readFont(arg[1]), readColor(arg[2]));
                break;

            case COLOR_OPTION_NAME:
                attributes = new ColorAttributes(readBool(arg[1]), readBool(arg[2]), readColor(arg[3]), readColor(arg[4]));
                break;

            case SELECT_OPTION_NAME:
                attributes = new SelectionAttributes(readBool(arg[1]));
                break;

            default:
                throw new CommandShapesException("bad attribute : " + arg[0]);
        }

        return attributes;
    }


    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length < 3) {
                throw new CommandShapesException("invalid argument number");
            }

            String[] attArgs = new String[args.length - 1];

            System.arraycopy(args, 1, attArgs, 0, args.length - 1);

            selectShape(processor, args[0]).addAttributes(createAttribute(attArgs));

        } catch (CommandShapesException e) {
            processor.err().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(' ');
        buffer.append("<id> <attribute name> <values>...");

        return new String(buffer);
    }
}
