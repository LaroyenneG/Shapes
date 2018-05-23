package processor.shapescommands;

import graphics.shapes.*;
import processor.engine.Processor;

public class CommandCreateShape extends CommandShapesEditor {

    public static final String RECT_OPTION_NAME = "rectangle";
    public static final String CIR_OPTION_NAME = "circle";
    public static final String TEXT_OPTION_NAME = "text";
    public static final String COL_OPTION_NAME = "collection";

    public CommandCreateShape() {
        super("create");
    }


    private static SRectangle createRectangle(String[] args) {

        return new SRectangle(readPoint(args[0], args[1]), readInt(args[2]), readInt(args[3]));
    }

    private static SCircle createCircle(String[] args) {
        return new SCircle(readPoint(args[0], args[1]), readInt(args[2]));
    }

    private static SText createText(String[] args) {
        return new SText(readPoint(args[0], args[1]), args[2]);
    }

    private static SCollection createSCollection(Processor processor, String[] args) throws CommandShapesException {

        SCollection collection = new SCollection();
        for (String s :
                args) {

            Shape shape = selectShape(processor, s);

            collection.add(shape);
            model(processor).deleteShape(shape);
        }

        return collection;
    }


    private static Shape createShape(Processor processor, String[] args) throws CommandShapesException {

        Shape shape = null;

        String[] nArgs = new String[args.length - 1];

        System.arraycopy(args, 1, nArgs, 0, args.length - 1);

        switch (args[0]) {

            case RECT_OPTION_NAME:
                shape = createRectangle(nArgs);
                break;

            case CIR_OPTION_NAME:
                shape = createCircle(nArgs);
                break;

            case TEXT_OPTION_NAME:
                shape = createText(nArgs);
                break;

            case COL_OPTION_NAME:
                shape = createSCollection(processor, nArgs);
                break;

            default:
                throw new CommandShapesException("invalid shape name");
        }

        return shape;
    }


    @Override
    public void execute(Processor processor, String[] args) {

        try {

            if (args.length < 3) {
                throw new CommandShapesException("invalid argument number");
            }

            SCollection collection = model(processor);
            Shape shape = createShape(processor, args);
            collection.add(shape);
            processor.out().print(shape.hashCode());
        } catch (CommandShapesException e) {
            processor.err().println(e.getMessage());
        }
    }

    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(super.toString());
        buffer.append(' ');
        buffer.append("<type> <values>...");

        return new String(buffer);
    }
}
