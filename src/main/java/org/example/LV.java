package org.example;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LV extends Canvas implements Runnable {
    LC controller;
    private int x = -1;
    private int y = -1;
    public LV(LC controller) {
        this.controller = controller;
        this.setBackground(Color.RED);
        this.setVisible(true);
        this.setSize(450, 450);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                controller.addBall(x, y);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (x != -1 && y != -1) {
            for (int i = 0; i < this.controller.model.objects.size(); i++) {
                this.controller.model.objects.get(i).paint(g);
            }
        }
    }


    @Override
    public void run() {
        while (true) {
            repaint();
        }
    }
}
