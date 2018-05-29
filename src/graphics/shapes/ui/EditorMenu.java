package graphics.shapes.ui;

import graphics.ui.Controller;
import graphics.ui.View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditorMenu extends JMenuBar {


    public static final String ID_CREDIT = "credit";
    public static final String ID_QUIT = "quit";
    public static final String ID_IMPORT = "importa";
    public static final String ID_EXPORT = "export";
    public static final String ID_RESET = "reset";
    public static final String ID_SCHOOL = "school";
    public static final String ID_MANUAL = "manual";
    public static final String ID_RECTAGLE = "rectangle";
    public static final String ID_CIRCLE = "circle";
    public static final String ID_TEXT = "text";

    private JMenu option;
    private JMenu help;
    private JMenu script;
    private JMenu info;
    private JMenu creator;

    private JMenuItem reset;
    private JMenuItem credit;
    private JMenuItem importa;
    private JMenuItem export;
    private JMenuItem quit;
    private JMenuItem school;
    private JMenuItem manual;
    private JMenuItem stext;
    private JMenuItem srectangle;
    private JMenuItem scircle;

    private List<JMenuItem> jMenuItems;

    private Controller controller;
    private Object model;

    public EditorMenu(Object model) {
        super();

        this.model = model;

        jMenuItems = new ArrayList<>();

        info = new JMenu("About");
        script = new JMenu("Script");
        help = new JMenu("Help");
        option = new JMenu("Options");
        creator = new JMenu("New");

        reset = new JMenuItem("Reset");
        credit = new JMenuItem("Credit");
        importa = new JMenuItem("Importation");
        export = new JMenuItem("Exportation");
        school = new JMenuItem("School");
        quit = new JMenuItem("Exit");
        manual = new JMenuItem("Manual");
        srectangle = new JMenuItem("Rectangle");
        scircle = new JMenuItem("Circle");
        stext = new JMenuItem("Text");

        controller = defaultController(model);

        jMenuItems.add(credit);
        jMenuItems.add(reset);
        jMenuItems.add(importa);
        jMenuItems.add(export);
        jMenuItems.add(school);
        jMenuItems.add(quit);
        jMenuItems.add(manual);
        jMenuItems.add(stext);
        jMenuItems.add(srectangle);
        jMenuItems.add(scircle);

        initAttributes();
        setController(controller);
    }


    public void initAttributes() {

        reset.setActionCommand(ID_RESET);
        credit.setActionCommand(ID_CREDIT);
        importa.setActionCommand(ID_IMPORT);
        export.setActionCommand(ID_EXPORT);
        school.setActionCommand(ID_SCHOOL);
        quit.setActionCommand(ID_QUIT);
        manual.setActionCommand(ID_MANUAL);
        scircle.setActionCommand(ID_CIRCLE);
        stext.setActionCommand(ID_TEXT);
        srectangle.setActionCommand(ID_RECTAGLE);

        option.add(reset);
        option.add(quit);

        help.add(manual);

        script.add(importa);
        script.add(export);

        info.add(credit);
        info.add(school);

        creator.add(stext);
        creator.add(scircle);
        creator.add(srectangle);

        add(option);
        add(creator);
        add(help);
        add(script);
        add(info);
    }


    public void setController(ActionListener listener) {
        for (JMenuItem item : jMenuItems) {
            item.addActionListener(listener);
        }
    }

    public void setView(View view) {
        controller.setView(view);
    }

    public Controller defaultController(Object model) {

        return new MenuController(model);
    }
}
