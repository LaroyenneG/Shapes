package processor.shapescommands;

import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import processor.engine.Processor;

public class CommandAddAttribute extends CommandShapesEditor {

    public CommandAddAttribute() {
        super("addatt");
    }


    private static Attributes createAttribute(String[] arg) throws CommandShapesException {


        Attributes attributes = null;

        switch (arg[0]) {

            case "font":
                attributes = new FontAttributes(readFont(arg[1]), readColor(arg[2]));
                break;

            case "color":
                attributes = new ColorAttributes(readBool(arg[1]), readBool(arg[2]), readColor(arg[3]), readColor(arg[4]));
                break;

            case "selection":
                attributes = new SelectionAttributes();
                break;

            default:
                throw new CommandShapesException("bad attribute");
        }

        return attributes;
    }


    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length < 2) {
                throw new CommandShapesException("invalid argument number");
            }

            String[] attAgrs = new String[args.length - 1];

            System.arraycopy(args, 1, attAgrs, 0, args.length - 1);

            selectShape(processor, args[1]).addAttributes(createAttribute(attAgrs));
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
