package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;


public class ShapesController extends Controller {

    private static final int HANDLER_SIZE = 5;

    private Point mousePosition;
    private Point mousePosition2;
    private int x, y;

    private boolean select;
    private boolean resize;

    public ShapesController(Object newModel) {

        super(newModel);
        mousePosition = new Point(0, 0);
        mousePosition2 = new Point(0, 0);
        x = 0;
        y = 0;
        select = false;
        resize = true;
    }

    public Shape getTarget(MouseEvent e) {

        SCollection collection = (SCollection) getModel();
        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getBounds().contains(e.getPoint())) {
                return shape;
            }
        }
        //Aucune figure est sélectionnée => return NULL
        return null;
    }

    public boolean getTargetHandler(MouseEvent e) {

        SCollection collection = (SCollection) getModel();
        Iterator<Shape> iterator = collection.iterator();


        while (iterator.hasNext()) {

            Shape shape = iterator.next();

            Rectangle bounds = shape.getBounds();
            Rectangle handInf = new Rectangle(bounds.x + bounds.width - HANDLER_SIZE, bounds.y + bounds.height - HANDLER_SIZE, HANDLER_SIZE, HANDLER_SIZE);

            if (handInf.contains(e.getPoint())) {
                return true;
            }
        }

        return false;
    }

    public void translateSelected(int dx, int dy) {

        SCollection collection = (SCollection) getModel();
        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Shape shape = iterator.next();

            if (((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).isSelected()) {
                shape.translate(dx, dy);
            }
        }
    }

    public void reSizeSelected(int dx, int dy) {

        SCollection collection = (SCollection) getModel();
        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Shape shape = iterator.next();

            if (((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).isSelected()) {
                shape.reSize(dx, dy);
            }
        }
    }

    public void unSelectAll() {

        SCollection collection = (SCollection) super.getModel();
        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            ((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).unSelect();
        }

        getView().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        Shape target = getTarget(e);
        if (target != null) {
            select = ((SelectionAttributes) target.getAttributes(SelectionAttributes.ID)).isSelected();
        }

        if (e.getButton() != MouseEvent.BUTTON3 && (target == null || !select)) {
            unSelectAll();
        }
        /*
        else if (target != null && select) {

        }
        */
        if (getTargetHandler(e)) {
            resize = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        mousePosition = e.getLocationOnScreen();

        x = (int) mousePosition.getX();
        y = (int) mousePosition.getY();

        int dx = x - mousePosition2.x;
        int dy = y - mousePosition2.y;

        if (!e.isShiftDown() && !resize) {
            translateSelected(dx, dy);
        }

        if (resize) {
            reSizeSelected(dx, dy);
        }

        mousePosition2 = e.getLocationOnScreen();

        getView().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        mousePosition = e.getLocationOnScreen();

        x = (int) mousePosition.getX();
        y = (int) mousePosition.getY();

        Shape target = getTarget(e);

        //Sélection traditionnelle
        if (target != null) {
            ((SelectionAttributes) target.getAttributes(SelectionAttributes.ID)).toggleSelection();
        }
        /*
        else if (!e.isShiftDown() && !getTargetHandler(e) && !multipleSelection && e.getButton() != MouseEvent.BUTTON3) {
            unSelectAll();
        } else {

        }
        */
        if (getTargetHandler(e)) {
            resize = true;
        }

        mousePosition2 = e.getLocationOnScreen();
        mousePosition = e.getLocationOnScreen();

        getView().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resize = false;
    }

    @Override
    public void keyTyped(KeyEvent evt) {

        if (evt.getKeyChar() == 's' || evt.getKeyChar() == 'S') {
            System.out.println(evt.getKeyChar());
        }
    }

}

