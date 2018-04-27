package graphics.shapes.attributes;

public class SelectionAttributes extends Attributes {

    private boolean selected;
    private String id;

    public String getId()
    {
        return id;
    }
    public boolean isSelected()
    {
        return selected;
    }
    public void select()
    {
        selected = true;
    }
    public void unselect()
    {
        selected = false;
    }
    public void toggleSelection()
    {
        if(selected)
        {
            unselect();
        }
        else
        {
            select();
        }
    }
}
