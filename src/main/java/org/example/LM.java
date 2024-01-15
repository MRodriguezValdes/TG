package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class LM {
    List<VO> objects;
    LC controller;
    Random random = new Random();


    public LM(LC controller) {
        this.controller = controller;
        this.objects = Collections.synchronizedList(new ArrayList<>());
    }

    public List<VO> getObjects() {
        return objects;
    }

    public void addBall(int x, int y) {
        Ball ball = new Ball(x, y, this);
        this.objects.add(ball);
        new Thread(ball).start();
    }

    public void collideDetection(Ball ball, Coordinate newPosition) {
        ArrayList<VO> objectsCollide= findCollisions(ball);

//      Si no hay colision , seteamos la posision
        if (objectsCollide.size() == 0) {
            ball.position = newPosition;

        } else {
//      Y si hay colision lo enviamos al collideManagement
            this.controller.collideManagement(objectsCollide);
            objectsCollide.clear();
        }

        checkWallCollision(ball);
    }

    private ArrayList<VO> findCollisions(VO vObject) {
        ArrayList<VO> objectsCollide = new ArrayList<>();
        BoundingBox vObjectBoundingBox = vObject.getBoundingBox();

        for (VO otherObject : objects) {
            if (otherObject != vObject) {
                BoundingBox otherObjectBoundingBox = otherObject.getBoundingBox();

                if (vObjectBoundingBox.intersects(otherObjectBoundingBox)) {
                    objectsCollide.add(vObject);
                    objectsCollide.add(otherObject);
                }
            }
        }

        return objectsCollide;
    }

    private void checkWallCollision(Ball ball) {
        if (ball.position.x < ball.radius) {
            ball.bounce();
            ball.position.x = ball.radius;
        } else if (ball.position.x > controller.view.getWidth() - ball.radius) {
            ball.bounce();
            ball.position.x = controller.view.getWidth() - ball.radius;
        }

        if (ball.position.y > controller.view.getHeight() - ball.radius) {
            ball.bounce();
            ball.position.y = controller.view.getHeight() - ball.radius;
        } else if (ball.position.y < ball.radius) {
            ball.bounce();
            ball.position.y = ball.radius;
        }
    }
}
