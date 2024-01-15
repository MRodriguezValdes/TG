package org.example;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

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
            List <VO> objectsToPaint = this.controller.model.objects;
            synchronized (objectsToPaint) {
                for (int i = 0; i < objectsToPaint.size(); i++) {
                    VO object =  objectsToPaint.get(i);
                    if (objectsToPaint.get(i).isAlive()) {
                        object.paint(g);
                    }else{
                        objectsToPaint.remove(object);
                    }
                }
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
