package graphics.shapes.ui;

import graphics.shapes.SCircle;
import graphics.shapes.SCollection;
import graphics.shapes.SRectangle;
import graphics.shapes.SText;
import graphics.shapes.attributes.ColorAttributes;
import graphics.shapes.attributes.FontAttributes;
import graphics.shapes.attributes.SelectionAttributes;
import processor.engine.Processor;
import processor.engine.ProcessorException;
import processor.shapescommands.*;

import javax.swing.*;
import java.awt.*;
import java.util.InputMismatchException;

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

        buildModel();

        sView = new ShapesView(model);
        sView.setPreferredSize(new Dimension(300, 300));

        getContentPane().add(sView, java.awt.BorderLayout.CENTER);

        menu = new EditorMenu(model);

        setJMenuBar(menu);
    }


    private void buildModel() {

        model = new SCollection();
        model.addAttributes(new SelectionAttributes());

        SRectangle r = new SRectangle(new Point(10, 10), 20, 30);
        r.addAttributes(new ColorAttributes(true, false, Color.BLUE, Color.BLUE));
        r.addAttributes(new SelectionAttributes());
        model.add(r);

        SCircle c = new SCircle(new Point(100, 100), 10);
        c.addAttributes(new ColorAttributes(false, true, Color.BLUE, Color.BLUE));
        c.addAttributes(new SelectionAttributes());
        model.add(c);

        SText t = new SText(new Point(100, 100), "hello");
        t.addAttributes(new ColorAttributes(true, true, Color.YELLOW, Color.BLUE));
        t.addAttributes(new FontAttributes());
        t.addAttributes(new SelectionAttributes());
        model.add(t);

        SCollection sc = new SCollection();
        sc.addAttributes(new SelectionAttributes());
        r = new SRectangle(new Point(20, 30), 30, 30);
        r.addAttributes(new ColorAttributes(true, false, Color.MAGENTA, Color.BLUE));
        r.addAttributes(new SelectionAttributes());
        sc.add(r);
        c = new SCircle(new Point(150, 100), 20);
        c.addAttributes(new ColorAttributes(false, true, Color.BLUE, Color.DARK_GRAY));
        c.addAttributes(new SelectionAttributes());
        sc.add(c);

        model.add(sc);
    }


    public static void main(String[] args) {

        if (args.length != 0) {
            System.err.println("Usage : java Editor");
            System.exit(-1);
        }

        Editor self = new Editor();
        self.pack();
        self.setVisible(true);

        Processor processor = new Processor();

        processor.addNewCommand(new CommandCreateShape());
        processor.addNewCommand(new CommandDeleteShape());
        processor.addNewCommand(new CommandMoveShape());
        processor.addNewCommand(new CommandListShapes());
        processor.addNewCommand(new CommandResizeShape());


        processor.setSystem(self.model);

        processor.setIn(System.in);
        processor.setOut(System.out);

        while (!processor.isTerminated()) {

            try {
                processor.printPrompt();
                processor.execute(processor.decode(processor.fetch()));
            } catch (ProcessorException e) {
                processor.out().println(e.getMessage());
            } catch (InputMismatchException e) {
                processor.out().println("bad input value");
            }
        }
    }
}

