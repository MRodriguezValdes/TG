package org.example;

import java.awt.*;
import java.util.Random;

public class Ball implements VO, Runnable {
    public final int[] moveX = {-1, 1, 0, 0, -1, -1, 1, 1};
    public final int[] moveY = {0, 0, -1, 1, 1, -1, 1, -1};

    int radio=10;
    public Coordinate position;
    private int direction;
    boolean isAlive;
    private int velocity = 5;
    private double acceleration = 10;
    private double mass;

    private LM model;

    public Ball(int x, int y, LM model) {
        this.position = new Coordinate();
        this.setDirection(new Random().nextInt(moveX.length));
        this.model = model;
        this.isAlive= true;

        this.position.x = x;
        this.position.y = y;
    }
    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
    public void nextMovement() {
        Coordinate newPosition = new Coordinate();
        newPosition.x = position.x + moveX[getDirection()] * velocity;
        newPosition.y = position.y + moveY[getDirection()] * velocity;

        this.model.collideDetection(this, newPosition);
        move();
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(position.x - 10, position.y - 10, radio*2, radio*2);
    }

    @Override
    public void move() {
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        position.x += moveX[getDirection()] * velocity;
        position.y += moveY[getDirection()] * velocity;
    }

    @Override
    public Coordinate getPosition() {
        return this.position;
    }

    @Override
    public void bounce() {
        this.setDirection(new Random().nextInt(moveX.length));
    }

    @Override
    public void kill() {
        isAlive = false;
    }

    @Override
    public void run() {
        while (isAlive) {
            this.nextMovement();
        }
    }


}
