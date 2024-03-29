package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {

    public static final String ID = "selection";

    private boolean selected;

    public SelectionAttributes() {
        selected = false;
    }

    public SelectionAttributes(boolean selected) {
        this.selected = selected;
    }


    @Override
    public String getId() {
        return ID;
    }

    public boolean isSelected() {

        return selected;
    }

    public void select() {

        selected = true;
    }

    public void unSelect() {

        selected = false;
    }

    public void toggleSelection() {

        if (selected) {
            unSelect();
        } else {
            select();
        }
    }
}
