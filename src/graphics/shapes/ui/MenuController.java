package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.ui.Controller;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class MenuController extends Controller {

    private JOptionPane dialog;

    public MenuController(Object newModel) {
        super(newModel);
        dialog = new JOptionPane();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JFileChooser chooser = null;

        switch (actionEvent.getActionCommand()) {

            case EditorMenu.ID_CREDIT:
                JOptionPane.showMessageDialog(dialog, "Create by :\n\tLucas COLLOT-PENICHOT\n\tGuillaume LAROYENNE\n\tAlexis DECKER-WURTZ\n\tChristopher BERTRAND", "Credit", JOptionPane.INFORMATION_MESSAGE);
                break;


            case EditorMenu.ID_QUIT:
                System.exit(0);
                break;

            case EditorMenu.ID_IMPORT:
                chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF Images", "jpg", "gif");
                chooser.setFileFilter(filter);
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (chooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
                    String filePath = chooser.getSelectedFile().getPath();
                    File file = new File(filePath);
                }
                break;

            case EditorMenu.ID_EXPORT:
                chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (chooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {
                    String directory = chooser.getSelectedFile().getPath();
                    System.out.println(directory);

                    String fileName = (String) JOptionPane.showInputDialog(
                            dialog,
                            "Input file name :",
                            "Files",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "");
                    if(fileName != null ){
                        File file = new File(directory+File.separator+fileName);
                    }
                }

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
