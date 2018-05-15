package graphics.shapes;

public interface ShapeVisitor {

    void visitRectangle(SRectangle rectangle);

    void visitCircle(SCircle circle);

    void visitCollection(SCollection collection);

    void visitText(SText text);

}
