package org.example;
import java.awt.*;

public class BoundingBox extends Rectangle {

    public BoundingBox(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2 - x1, y2 - y1);
    }
}
