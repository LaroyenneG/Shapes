package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.ui.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuController extends Controller {

    private JOptionPane dialog;

    public MenuController(Object newModel) {
        super(newModel);
        dialog = new JOptionPane();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        switch (actionEvent.getActionCommand()) {

            case EditorMenu.ID_CREDIT:
                JOptionPane.showMessageDialog(dialog, "Create by :\n\tLucas COLLOT-PENICHOT\n\tGuillaume LAROYENNE\n\tAlexis DECKER-WURTZ\n\tChristopher BERTRAND", "Credit", JOptionPane.INFORMATION_MESSAGE);
                break;


            case EditorMenu.ID_QUIT:
                System.exit(0);
                break;

            case EditorMenu.ID_RESET:
                ((SCollection) getModel()).deleteAllShapes();
                getView().repaint();
                break;

            case EditorMenu.ID_SCHOOL:
                JOptionPane.showMessageDialog(dialog, "ENSISA\nhttp://www.ensisa.uha.fr/\n12 Rue des Frères Lumière\n68093 Mulhouse\nFrance", "School", JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                break;
        }
    }
}
