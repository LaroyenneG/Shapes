package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.ui.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController extends Controller implements ActionListener {

    public MenuController(Object newModel) {
        super(newModel);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {

            case EditorMenu.ID_CREDIT:
                JOptionPane dialog = new JOptionPane();
                JOptionPane.showMessageDialog(dialog, "Create by :\n\tLAROYENNE Guillaume\n\tLucas...", "Credit", JOptionPane.INFORMATION_MESSAGE);
                break;


            case EditorMenu.ID_QUIT:
                System.exit(0);
                break;

            case EditorMenu.ID_RESET:
                ((SCollection) getModel()).deleteAllShapes();
                /*
                repaint
                 */
                break;

            default:
                break;
        }
    }
}
