package graphics.ui;

import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.shapes.ui.ShapesController;
import javafx.scene.control.SeparatorMenuItem;

import java.awt.event.*;

public class Controller implements MouseListener, MouseMotionListener, KeyListener {

    private Object model;
    private View view;


    public Controller(Object newModel) {

        model = newModel;
    }

    final public View getView() {

        return view;
    }

    public void setView(View view) {

        this.view = view;
    }

    public Object getModel() {
        return model;
    }

    public void setModel(Object model) {

        this.model = model;
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent evt) {
    }

    public void mouseDragged(MouseEvent evt) {
    }

    public void keyTyped(KeyEvent evt) {
    }

    public void keyPressed(KeyEvent evt) {
    }

    public void keyReleased(KeyEvent evt) {
    }
}
