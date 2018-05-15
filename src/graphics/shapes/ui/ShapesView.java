package graphics.shapes.ui;

import graphics.shapes.Shape;
import graphics.ui.View;

import java.awt.*;


public class ShapesView extends View {

    public ShapesView(Object model) {

        super(model);
    }


    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Shape model = (Shape) this.getModel();

        if (model == null) {
            return;
        }

        ShapeDraftman draftman = new ShapeDraftman(g);

        model.accept(draftman);
    }
}
