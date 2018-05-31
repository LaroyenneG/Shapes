package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;

import static graphics.shapes.ui.ShapeDraftman.HANDLER_SIZE;


public class ShapesController extends Controller {


    private Point mousePosition;

    private boolean select;
    private boolean resize;

    public ShapesController(Object newModel) {

        super(newModel);

        mousePosition = new Point(0, 0);

        select = false;
        resize = true;
    }

    public Shape getTarget() {

        SCollection collection = (SCollection) getModel();
        Iterator<Shape> iterator = collection.iterator();

        while (iterator.hasNext()) {
            Shape shape = iterator.next();
            if (shape.getBounds().contains(mousePosition)) {
                return shape;
            }
        }
        //Aucune figure est sélectionnée => return NULL
        return null;
    }

    public boolean getTargetHandler() {

        SCollection collection = (SCollection) getModel();
        Iterator<Shape> iterator = collection.iterator();


        while (iterator.hasNext()) {

            Shape shape = iterator.next();

            Rectangle bounds = shape.getBounds();
            Rectangle handInf = new Rectangle(bounds.x + bounds.width - HANDLER_SIZE, bounds.y + bounds.height - HANDLER_SIZE, HANDLER_SIZE, HANDLER_SIZE);

            if (handInf.contains(mousePosition)) {
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


    private void changeMousePosition(MouseEvent e) {
        mousePosition = e.getPoint();
    }


    @Override
    public void mousePressed(MouseEvent e) {

        changeMousePosition(e);

        Shape target = getTarget();
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
        if (getTargetHandler()) {
            resize = true;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        int dx = e.getPoint().x - mousePosition.x;
        int dy = e.getPoint().y - mousePosition.y;

        System.out.println(dx + "," + dy);

        if (!e.isShiftDown() && !resize) {
            translateSelected(dx, dy);
        }

        if (resize) {
            reSizeSelected(dx, dy);
        }

        changeMousePosition(e);

        getView().repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        changeMousePosition(e);

        Shape target = getTarget();

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


        if (getTargetHandler()) {
            resize = true;
        }


        getView().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        resize = false;
    }

    @Override
    public void keyTyped(KeyEvent evt) {

        if (evt.getKeyChar() == 'd' || evt.getKeyChar() == 'D') {


            Shape target = getTarget();

            if (target != null) {
                ((SCollection) getModel()).deleteShape(target);
            }
        }

        getView().repaint();
    }
}

