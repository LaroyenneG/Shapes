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

    public static final String HEADER_MESSAGE = " Shapes script auto generated";

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
                    .append(convertColor(color.filledColor))
                    .append(' ')
                    .append(convertColor(color.strokedColor));
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
                    .append(convertFont(font.font))
                    .append(Processor.QUOTES)
                    .append(' ')
                    .append(convertColor(font.fontColor));
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
        return "s" + String.valueOf(dictionary++);
    }

    private static StringBuilder buildSubCommand(String key, StringBuilder command) {

        final String commandName = new CommandCreateShape().getName();


        StringBuilder string = new StringBuilder();

        string.append(Processor.VARIABLE_CHAR)
                .append(key)
                .append(Processor.COMMAND_AFFECTATION)
                .append(Processor.SEP_CHAR_1)
                .append(commandName)
                .append(' ')
                .append(command)
                .append(Processor.SEP_CHAR_2)
                .append(BR);

        return string;
    }

    private List<String> buildCommandFromSCollection(StringBuilder line, SCollection shapes) throws CommandShapesException {

        line.append(BR);

        List<String> keyList = new ArrayList<>();

        Iterator<Shape> iterator = shapes.iterator();

        while (iterator.hasNext()) {

            Shape shape = iterator.next();

            String key = getNextName();

            StringBuilder command = new StringBuilder();

            if (shape instanceof SCollection) {

                List<String> subKeyList = buildCommandFromSCollection(line, (SCollection) shape);

                command.append(CommandCreateShape.COL_OPTION_NAME);

                for (String k :
                        subKeyList) {

                    command.append(' ')
                            .append(Processor.VARIABLE_CHAR)
                            .append(k);
                }

            } else if (shape instanceof SRectangle) {

                SRectangle rectangle = (SRectangle) shape;

                command.append(CommandCreateShape.RECT_OPTION_NAME)
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

                command.append(CommandCreateShape.CIR_OPTION_NAME)
                        .append(' ')
                        .append(circle.getLoc().x)
                        .append(' ')
                        .append(circle.getLoc().y)
                        .append(' ')
                        .append(circle.getRadius());

            } else if (shape instanceof SText) {

                SText text = (SText) shape;

                command.append(CommandCreateShape.TEXT_OPTION_NAME)
                        .append(' ')
                        .append(text.getLoc().x)
                        .append(' ')
                        .append(text.getLoc().y)
                        .append(' ')
                        .append(Processor.QUOTES)
                        .append(text.getText())
                        .append(Processor.QUOTES);

            } else {
                throw new CommandShapesException("invalid object signature");
            }

            line.append(buildSubCommand(key, command));

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

            commands.append(BR)
                    .append("clear")
                    .append(BR);

            buildCommandFromSCollection(commands, model(processor));

            writer.write(new String(commands));

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
