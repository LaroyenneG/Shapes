package graphics.shapes.ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EditorMenu extends JMenuBar {


    public static final String ID_CREDIT = "credit";
    public static final String ID_QUIT = "quit";
    public static final String ID_RESET = "reset";


    private JMenu option;
    private JMenu info;

    private JMenuItem reset;
    private JMenuItem credit;
    private JMenuItem quit;

    public EditorMenu() {
        super();

        initAttributes();
    }


    public void initAttributes() {

        info = new JMenu("Info");
        option = new JMenu("Options");

        reset = new JMenuItem("Reset");
        reset.setActionCommand(ID_RESET);

        credit = new JMenuItem("Credit");
        credit.setActionCommand(ID_CREDIT);

        quit = new JMenuItem("Quiter");
        quit.setActionCommand(ID_QUIT);

        option.add(reset);
        option.add(quit);

        info.add(credit);

        add(option);
        add(info);
    }


    public void setController(ActionListener listener) {

        credit.addActionListener(listener);
        quit.addActionListener(listener);
    }
}
