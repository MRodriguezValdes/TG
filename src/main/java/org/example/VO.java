package org.example;

import java.awt.*;

public interface VO {
    void paint(Graphics g);

    void move();

    BoundingBox getBoundingBox();

    void bounce();

    void kill();
}
