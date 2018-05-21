package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.attributes.Attributes;
import graphics.shapes.attributes.SelectionAttributes;
import graphics.ui.Controller;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;

public class ShapesController extends Controller {

    private Point mouseposition = new Point(0, 0);
    private Point mouseposition2 = new Point(0, 0);
    private int x = 0;
    private int y = 0;
    private boolean select = false;

    public ShapesController(Object newModel) {
        super(newModel);
    }

    public Shape getTarget(MouseEvent e)
    {
        SCollection collection = (SCollection) super.getModel();

        for (Iterator<Shape> iter = collection.iterator(); iter.hasNext(); ) {
            Shape shape = iter.next();
            if (shape.getBounds().contains(e.getPoint())) {
                return shape;
            }
        }
        //Aucune figure est sélectionnée => return NULL
        return null;
    }

    public void translateSelected(int dx, int dy) {
        SCollection collection = (SCollection) super.getModel();

        for (Iterator<Shape> iter = collection.iterator(); iter.hasNext(); ) {
            Shape shape = iter.next();
            if (((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).isSelected()) {
                shape.translate(dx, dy);
            }
        }

    }

    public void unselectAll() {
        SCollection collection = (SCollection) super.getModel();
        for (Iterator<Shape> iter = collection.iterator(); iter.hasNext(); ) {
            Shape shape = iter.next();
            ((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).unSelect();
        }
        super.getView().repaint();
    }

    public void mousePressed(MouseEvent e) {
        mouseposition = e.getLocationOnScreen();
        x = (int) mouseposition.getX();
        y = (int) mouseposition.getY();
        Shape target = this.getTarget(e);
        Attributes attributes = target.getAttributes(SelectionAttributes.ID);
        select = ((SelectionAttributes) attributes).isSelected();

        if (target == null || !select) {

        } else if (target != null && select) {

        }

    }

    public void mouseDragged(MouseEvent e) {
        int dx = mouseposition.x - mouseposition2.x;
        int dy = mouseposition.y - mouseposition2.y;

        translateSelected(dx, dy);
        super.getView().repaint();
    }

    public void mouseClicked(MouseEvent e) {
        mouseposition = e.getLocationOnScreen();
        x = (int) mouseposition.getX();
        y = (int) mouseposition.getY();
        Shape target = this.getTarget(e);

        if (!e.isShiftDown()) {
            unselectAll();
        } else {

        }
        if (target != null) {
            ((SelectionAttributes) target.getAttributes(SelectionAttributes.ID)).toggleSelection();
            if (e.getButton() != 1) {
                ((SelectionAttributes) target.getAttributes(SelectionAttributes.ID)).select();
            }
        }

        super.getView().repaint();
        mouseposition2 = e.getLocationOnScreen();
        mouseposition = e.getLocationOnScreen();
    }


}

