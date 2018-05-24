package graphics.shapes.ui;

import graphics.ui.Controller;
import processor.engine.Processor;
import processor.engine.ProcessorException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.io.File;

public class MenuController extends Controller {

    private static final String FILE_EXT = "shapes";

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

            case EditorMenu.ID_IMPORT:
                importFile();
                break;

            case EditorMenu.ID_EXPORT:
                exportFile();
                break;

            case EditorMenu.ID_RESET:
                resetView();
                break;

            case EditorMenu.ID_SCHOOL:
                JOptionPane.showMessageDialog(dialog, "ENSISA\nhttp://www.ensisa.uha.fr/\n12 Rue des Frères Lumière\n68093 Mulhouse\nFrance", "School", JOptionPane.INFORMATION_MESSAGE);
                break;

            default:
                break;
        }
    }

    private void resetView() {

        Processor processor = Processor.getInstance();
        try {
            processor.interpretLine("clear");
            getView().repaint();
        } catch (ProcessorException e) {
            e.printStackTrace();
        }
    }

    private void importFile() {

        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Shapes Model", FILE_EXT);
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (chooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {

            String filePath = chooser.getSelectedFile().getPath();

            File file = new File(filePath);

            Processor processor = Processor.getInstance();
            try {
                processor.interpretLine("script " + file.getAbsolutePath());
                getView().repaint();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(dialog, "Error to import file", "Shapes has encountered a problem", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void exportFile() {

        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(dialog) == JFileChooser.APPROVE_OPTION) {

            String directory = chooser.getSelectedFile().getPath();

            String fileName = (String) JOptionPane.showInputDialog(
                    dialog,
                    "Input file name :",
                    "Files",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    "");

            if (fileName != null) {

                File file = new File(directory + File.separator + fileName + "." + FILE_EXT);

                Processor processor = Processor.getInstance();
                try {
                    processor.interpretLine("export " + file.getAbsolutePath());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(dialog, "Error to export file", "Shapes has encountered a problem", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
