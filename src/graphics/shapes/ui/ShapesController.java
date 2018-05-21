package graphics.shapes.ui;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
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
                System.out.println("go One");
                System.out.print(shape);
                System.out.print(" ");
                System.out.println(((SelectionAttributes) shape.getAttributes(SelectionAttributes.ID)).isSelected());
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
        System.out.print(dx);
        System.out.print(":");
        System.out.print(dy);
        System.out.println(" ");

        if (!e.isShiftDown()) {

            System.out.print("go translate");
            translateSelected(dx, dy);
        }
        mouseposition2 = e.getLocationOnScreen();
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
        }

        super.getView().repaint();
        mouseposition2 = e.getLocationOnScreen();
        mouseposition = e.getLocationOnScreen();
    }

    public void mouseReleased(MouseEvent e) {

    }


}

