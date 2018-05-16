package graphics.shapes.ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditorMenu extends JMenuBar {


    public static final String ID_CREDIT = "credit";
    public static final String ID_QUIT = "quit";
    public static final String ID_RESET = "reset";
    public static final String ID_SCHOOL = "school";

    private JMenu option;
    private JMenu info;

    private JMenuItem reset;
    private JMenuItem credit;
    private JMenuItem quit;
    private JMenuItem school;

    private List<JMenuItem> jMenuItems;

    public EditorMenu() {
        super();
        jMenuItems = new ArrayList<>();

        info = new JMenu("Info");
        option = new JMenu("Options");

        reset = new JMenuItem("Reset");
        credit = new JMenuItem("Credit");
        school = new JMenuItem("School");
        quit = new JMenuItem("Quiter");

        jMenuItems.add(credit);
        jMenuItems.add(reset);
        jMenuItems.add(school);
        jMenuItems.add(quit);
        initAttributes();
    }


    public void initAttributes() {

        reset.setActionCommand(ID_RESET);
        credit.setActionCommand(ID_CREDIT);
        school.setActionCommand(ID_SCHOOL);
        quit.setActionCommand(ID_QUIT);

        option.add(reset);
        option.add(quit);

        info.add(credit);
        info.add(school);

        add(option);
        add(info);
    }


    public void setController(ActionListener listener) {
        for (JMenuItem item : jMenuItems) {
            item.addActionListener(listener);
        }
    }
}