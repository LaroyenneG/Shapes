package graphics.shapes.ui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Manuel extends JFrame {

    private JPanel panel;
    private JTextArea area;
    private static final String PATH = "Manuel.text";

    public Manuel() {
        super("Manuel");
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        panel = new JPanel();
        area = new JTextArea();
        panel.add(area);
        getContentPane().add(panel, java.awt.BorderLayout.CENTER);
        area.setPreferredSize(new Dimension(600, 800));
        pack();
        area.setEditable(false);

    }

    public void readerManuel() {
        File f = new File(PATH);
        try {
            FileReader fr = new FileReader(f);
            BufferedReader reader = new BufferedReader(fr);

            StringBuilder text = new StringBuilder();

            String line = null;

            while ((line = reader.readLine()) != null) {
                text.append(line);
                text.append(System.getProperty("line.separator"));
            }

            area.append(text.toString());
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
