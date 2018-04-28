package graphics.ui;

import javax.swing.*;


public abstract class View extends JPanel {

    private Object model;
    private Controller controller;

    public View(Object model) {

        this.model = model;
        controller = defaultController(model);
        controller.setView(this);
        addMouseListener(controller);
        addMouseMotionListener(controller);
        addKeyListener(controller);
    }

    public Object getModel() {

        return this.model;
    }

    public void setModel(Object model) {

        this.model = model;
        controller.setModel(model);
    }

    public Controller defaultController(Object model) {

        return new Controller(model);
    }

    final public Controller getController() {

        return controller;
    }
}


