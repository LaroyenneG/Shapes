package graphics.ui;

import java.awt.event.*;
/*
Ne pas modifier la class faire des class filles
 */

public class Controller implements MouseListener, MouseMotionListener, KeyListener, ActionListener {

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

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent evt) {
    }

    @Override
    public void mouseDragged(MouseEvent evt) {
    }

    @Override
    public void keyTyped(KeyEvent evt) {
    }

    @Override
    public void keyPressed(KeyEvent evt) {
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
    }
}
