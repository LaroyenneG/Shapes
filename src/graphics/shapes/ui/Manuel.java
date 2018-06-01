package graphics.shapes.ui;

import javax.swing.*;
import java.io.*;

public class Manuel extends JFrame {

    private static final String PATH = "Manuel.text";
    private JTextArea area;
    private JScrollPane scroll;

    public Manuel() {
        super("Manuel");

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        area = new JTextArea(35, 50);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scroll);
        setLocationRelativeTo(null);
        pack();
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