package org.example;

import java.awt.*;

public interface VO {
    void paint(Graphics g);

    void move();

    Coordinate getPosition();

    void bounce();

    void kill();
}
