package processor.shapescommands;

import graphics.shapes.*;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import processor.engine.Processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CommandExportModel extends CommandShapesEditor {

    public static final String HEADER_MESSAGE = "Shapes script auto generated";
    private static final String BR = System.getProperty("line.separator");
    private int dictionary;

    public CommandExportModel() {
        super("export");
    }

    private static String buildAttributesCommand(Shape shape, String varName) {

        final String commandName = new CommandAddAttribute().getName();

        StringBuilder command = new StringBuilder();

        ColorAttributes color = (ColorAttributes) shape.getAttributes(ColorAttributes.ID);

        if (color != null) {
            command.append(commandName)
                    .append(' ')
                    .append(Processor.VARIABLE_CHAR)
                    .append(varName).append(' ')
                    .append(CommandAddAttribute.COLOR_OPTION_NAME)
                    .append(' ')
                    .append(String.valueOf(color.filled))
                    .append(' ')
                    .append(String.valueOf(color.stroked))
                    .append(" ")
                    .append(converColor(color.filledColor))
                    .append(' ')
                    .append(converColor(color.strokedColor));
        }

        command.append(BR);


        FontAttributes font = (FontAttributes) shape.getAttributes(FontAttributes.ID);

        if (font != null) {
            command.append(commandName)
                    .append(' ')
                    .append(Processor.VARIABLE_CHAR)
                    .append(varName).append(' ')
                    .append(CommandAddAttribute.FONT_OPTION_NAME)
                    .append(' ')
                    .append(Processor.QUOTES)
                    .append(font.font.getName())
                    .append(Processor.QUOTES)
                    .append(' ')
                    .append(converColor(font.fontColor));
        }


        command.append(BR);


        SelectionAttributes selection = (SelectionAttributes) shape.getAttributes(SelectionAttributes.ID);
        if (selection != null) {
            command.append(commandName)
                    .append(' ')
                    .append(Processor.VARIABLE_CHAR)
                    .append(varName)
                    .append(' ')
                    .append(CommandAddAttribute.SELECT_OPTION_NAME)
                    .append(' ')
                    .append(String.valueOf(selection.isSelected()));
        }

        command.append(BR);

        return new String(command);
    }

    private String getNextName() {
        return String.valueOf(dictionary++);
    }


    private List<String> buildCommandFromSCollection(StringBuilder line, SCollection shapes) throws CommandShapesException {

        line.append(BR);

        final String commandName = new CommandCreateShape().getName();

        List<String> keyList = new ArrayList<>();

        Iterator<Shape> iterator = shapes.iterator();

        while (iterator.hasNext()) {

            Shape shape = iterator.next();

            String key = getNextName();

            line.append(Processor.VARIABLE_CHAR)
                    .append(key)
                    .append(Processor.COMMAND_AFFECTATION)
                    .append(Processor.SEP_CHAR_1);

            line.append(commandName).append(' ');

            if (shape instanceof SCollection) {

                List<String> subKeyList = buildCommandFromSCollection(line, (SCollection) shape);

                line.append(CommandCreateShape.COL_OPTION_NAME);

                for (String k :
                        subKeyList) {

                    line.append(' ')
                            .append(Processor.VARIABLE_CHAR)
                            .append(k);
                }

            } else if (shape instanceof SRectangle) {

                SRectangle rectangle = (SRectangle) shape;

                line.append(CommandCreateShape.RECT_OPTION_NAME)
                        .append(' ')
                        .append(rectangle.getLoc().x)
                        .append(' ')
                        .append(rectangle.getLoc().y)
                        .append(' ')
                        .append(rectangle.getRect().width)
                        .append(' ')
                        .append(rectangle.getRect().height);

            } else if (shape instanceof SCircle) {

                SCircle circle = (SCircle) shape;

                line.append(CommandCreateShape.CIR_OPTION_NAME)
                        .append(' ')
                        .append(circle.getLoc().x)
                        .append(' ')
                        .append(circle.getLoc().y)
                        .append(' ')
                        .append(circle.getRadius());

            } else if (shape instanceof SText) {

                SText text = (SText) shape;

                line.append(CommandCreateShape.TEXT_OPTION_NAME)
                        .append(' ')
                        .append(text.getLoc().x)
                        .append(' ')
                        .append(text.getLoc().y)
                        .append(' ')
                        .append(text.getText());

            } else {
                throw new CommandShapesException("invalid object signature");
            }

            line.append(Processor.SEP_CHAR_2);

            line.append(BR);

            line.append(buildAttributesCommand(shape, key));

            line.append(BR);

            keyList.add(key);
        }
        return keyList;
    }

    @Override
    public void execute(Processor processor, String[] args) {

        dictionary = 0;
        try {
            if (args.length != 1) {
                throw new CommandShapesException("invalid argument");
            }

            File file = new File(args[0]);

            FileWriter writer = new FileWriter(file);

            StringBuilder commands = new StringBuilder(Processor.COMMAND_CHAR + HEADER_MESSAGE + BR);

            commands.append(BR).append("clear");

            buildCommandFromSCollection(commands, model(processor));

            writer.write(new String(commands));

            writer.append(commands);
            writer.flush();
            writer.close();

        } catch (CommandShapesException | FileNotFoundException e) {
            processor.err().println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
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