package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import processor.engine.Processor;
import processor.engine.ProcessorException;
import processor.shapescommands.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Editor extends JFrame {

    private ShapesView sView;
    private EditorMenu menu;
    private SCollection model;

    public Editor() {

        super("Shapes Editor");

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        model = new SCollection();

        sView = new ShapesView(model);
        sView.setPreferredSize(new Dimension(300, 300));

        getContentPane().add(sView, java.awt.BorderLayout.CENTER);

        menu = new EditorMenu(model);
        menu.setView(sView);

        setJMenuBar(menu);
    }


    private static void buildShapesForExample(SCollection model) {

        SRectangle r = new SRectangle(new Point(10, 10), 20, 30);
        r.addAttributes(new ColorAttributes(true, false, Color.BLUE, Color.BLUE));
        model.add(r);

        SCircle c2 = new SCircle(new Point(200, 200), 50);
        c2.addAttributes(new ColorAttributes(false, true, Color.BLUE, Color.BLUE));
        model.add(c2);


        SCircle c = new SCircle(new Point(100, 100), 10);
        c.addAttributes(new ColorAttributes(false, true, Color.BLUE, Color.BLUE));
        model.add(c);

        SText t = new SText(new Point(0, 0), "hello");
        t.addAttributes(new ColorAttributes(true, true, Color.YELLOW, Color.BLUE));
        model.add(t);

        SCollection sc = new SCollection();
        sc.addAttributes(new SelectionAttributes());
        SRectangle r2 = new SRectangle(new Point(20, 30), 30, 30);
        r2.addAttributes(new ColorAttributes(true, false, Color.MAGENTA, Color.BLUE));
        sc.add(r2);

        c = new SCircle(new Point(150, 100), 20);
        c.addAttributes(new ColorAttributes(false, true, Color.BLUE, Color.DARK_GRAY));
        sc.add(c);

        model.add(sc);
    }


    public static void main(String[] args) {

        if (args.length != 0) {
            System.err.println("Usage : java -jar Shapes.jar");
            System.exit(-1);
        }

        Editor self = new Editor();
        self.pack();
        self.setVisible(true);

        buildShapesForExample(self.model);

        Processor processor = Processor.getInstance();

        processor.addNewCommand(new CommandCreateShape());
        processor.addNewCommand(new CommandDeleteShape());
        processor.addNewCommand(new CommandMoveShape());
        processor.addNewCommand(new CommandListShapes());
        processor.addNewCommand(new CommandResizeShape());
        processor.addNewCommand(new CommandAddAttribute());
        processor.addNewCommand(new CommandExportModel());

        processor.setSystem(self.model);
        processor.setIn(System.in);
        processor.setOut(System.out);
        processor.setErr(System.err);

        while (!processor.isTerminated()) {

            try {
                processor.printPrompt();
                processor.interpretLine(processor.fetch());
                System.gc();
                self.sView.repaint();
            } catch (ProcessorException e) {
                processor.err().println(e.getMessage());
            } catch (NumberFormatException e) {
                processor.err().println("bad input value");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

