package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;


public class ShapesController extends Controller {

    private Point mouseposition;
    private Point mouseposition2;
    private int x, y;

    private boolean select;

    public ShapesController(Object newModel) {

        super(newModel);
        mouseposition = new Point(0, 0);
        mouseposition2 = new Point(0, 0);
        x = 0;
        y = 0;
        select = false;
    }

    public Shape getTarget(MouseEvent e)
    {
        SCollection collection = (SCollection) super.getModel();
        Iterator<Shape> iter = collection.iterator();

        while (iter.hasNext()) {
            Shape shape = iter.next();
            if (shape.getBounds().contains(e.getPoint())) {
                return shape;
            }
        }
        //Aucune figure est sélectionnée => return NULL
        return null;
    }

    public boolean getTargetHandler(MouseEvent e) {
        SCollection collection = (SCollection) super.getModel();
        Iterator<Shape> iter = collection.iterator();

        while (iter.hasNext()) {
            Shape shape = iter.next();
            Rectangle bounds = shape.getBounds();
            Rectangle handSup = new Rectangle(bounds.x, bounds.y, 10, 10);
            Rectangle handInf = new Rectangle(bounds.x + bounds.width - 10, bounds.x + bounds.width - 10, 10, 10);
            if (handSup.contains(e.getPoint()) || handInf.contains(e.getPoint())) {
                return true;
            }
        }
        //Aucune figure est sélectionnée => return NULL
        return false;
    }

    public void translateSelected(int dx, int dy) {
        SCollection collection = (SCollection) super.getModel();
        Iterator<Shape> iter = collection.iterator();

        while (iter.hasNext()) {
            Shape shape = iter.next();

            if (((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).isSelected()) {

                shape.translate(dx, dy);
            }
        }

    }

    public void reSizeSelected(int dx, int dy) {
        SCollection collection = (SCollection) super.getModel();
        Iterator<Shape> iter = collection.iterator();

        while (iter.hasNext()) {
            Shape shape = iter.next();

            if (((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).isSelected()) {

                shape.reSize(dx, dy);
            }
        }

    }

    public void unselectAll() {
        SCollection collection = (SCollection) super.getModel();
        Iterator<Shape> iter = collection.iterator();

        while (iter.hasNext()) {
            Shape shape = iter.next();
            ((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).unSelect();
        }
        super.getView().repaint();
    }

    public void mousePressed(MouseEvent e) {

        Shape target = this.getTarget(e);
        if (target != null) {
            select = ((SelectionAttributes) target.getAttributes(SelectionAttributes.ID)).isSelected();
        }

        if (target == null || !select) {
            unselectAll();
        } else if (target != null && select) {

        }

    }

    public void mouseDragged(MouseEvent e) {
        mouseposition = e.getLocationOnScreen();
        x = (int) mouseposition.getX();
        y = (int) mouseposition.getY();
        int dx = x - mouseposition2.x;
        int dy = y - mouseposition2.y;


        if (!e.isShiftDown()) {

            translateSelected(dx, dy);
            if (getTargetHandler(e)) {

                reSizeSelected(dx, dy);
            }
        }

        mouseposition2 = e.getLocationOnScreen();
        super.getView().repaint();
    }

    public void mouseClicked(MouseEvent e) {
        mouseposition = e.getLocationOnScreen();
        x = (int) mouseposition.getX();
        y = (int) mouseposition.getY();
        Shape target = getTarget(e);

        if (!e.isShiftDown()) {
            unselectAll();
        } else {

        }
        if (target != null) {
            ((SelectionAttributes) target.getAttributes(SelectionAttributes.ID)).toggleSelection();
        }

        super.getView().repaint();
        mouseposition2 = e.getLocationOnScreen();
        mouseposition = e.getLocationOnScreen();
    }

    public void mouseReleased(MouseEvent e) {

    }


}

