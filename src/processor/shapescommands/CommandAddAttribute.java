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


    private static Attributes createAttribute(Processor processor) throws CommandShapesException {


        Attributes attributes = null;

        switch (processor.scanner().next()) {

            case "font":
                attributes = new FontAttributes(readFont(processor), readColor(processor));
                break;

            case "color":
                attributes = new ColorAttributes(readBool(processor), readBool(processor), readColor(processor), readColor(processor));
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
    public void execute(Processor processor) {

        try {
            selectShape(processor).addAttributes(createAttribute(processor));
        } catch (CommandShapesException e) {
            processor.out().println(e.getMessage());
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
