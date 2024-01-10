package org.example;
import java.awt.*;

public class BoundingBox extends Rectangle implements Shape {

    public BoundingBox(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2 - x1, y2 - y1);
    }

    // Implementación del método intersects de la interfaz Shape
    @Override
    public boolean intersects(Rectangle rect) {
        return super.intersects(rect);
    }
}
