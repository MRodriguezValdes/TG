package org.example;

import java.awt.*;

public interface VO {
    void paint(Graphics g);

    void move();

    BoundingBox getBoundingBox();

    boolean isAlive();

    void bounce();

    void kill();
}
