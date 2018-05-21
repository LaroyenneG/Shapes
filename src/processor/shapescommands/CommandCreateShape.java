package processor.shapescommands;

import graphics.shapes.*;
import processor.engine.Processor;

public class CommandCreateShape extends CommandShapesEditor {

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

        SCollection sCollection = new SCollection();
        for (String s :
                args) {
            sCollection.add(selectShape(processor, s));
        }

        return sCollection;
    }


    private static Shape createShape(Processor processor, String[] args) throws CommandShapesException {

        Shape shape = null;

        String[] nArgs = new String[args.length - 1];

        System.arraycopy(args, 1, nArgs, 0, args.length - 1);

        switch (args[0]) {

            case "rectangle":
                shape = createRectangle(nArgs);
                break;

            case "circle":
                shape = createCircle(nArgs);
                break;

            case "text":
                shape = createText(nArgs);
                break;

            case "collection":
                shape = createSCollection(processor, args);
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
            processor.out().println(shape.hashCode());
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
