package processor.shapescommands;

import graphics.shapes.*;
import processor.engine.Processor;

public class CommandCreateShape extends CommandShapesEditor {

    public CommandCreateShape() {
        super("create");
    }


    private static SRectangle createRectangle(Processor processor) {

        return new SRectangle(readPoint(processor), readInt(processor), readInt(processor));
    }

    private static SCircle createCircle(Processor processor) {
        return new SCircle(readPoint(processor), readInt(processor));
    }

    private static SText createText(Processor processor) {
        return new SText(readPoint(processor), readString(processor));
    }


    private static Shape createShape(Processor processor) throws CommandShapesException {

        Shape shape = null;

        switch (processor.scanner().next()) {

            case "rectangle":
                shape = createRectangle(processor);
                break;

            case "circle":
                shape = createCircle(processor);
                break;

            case "text":
                shape = createText(processor);
                break;

            default:
                throw new CommandShapesException("invalid shape name");
        }

        return shape;
    }


    @Override
    public void execute(Processor processor) {

        SCollection collection = model(processor);

        try {
            collection.add(createShape(processor));
        } catch (CommandShapesException e) {
            processor.out().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(" ");
        buffer.append("<type> <Values>...");

        return new String(buffer);
    }
}
