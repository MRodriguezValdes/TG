package org.example;

import java.util.ArrayList;
import java.util.Random;

public class LM {
    ArrayList<VO> objects;

    LC controller;
    Random random = new Random();


    public LM(LC controller) {
        this.controller = controller;
        this.objects = new ArrayList<>();
    }

    public ArrayList<VO> getObjects() {
        return objects;
    }

    public void addBall(int x, int y) {
        Ball ball = new Ball(x, y, this);
        this.objects.add(ball);
        new Thread(ball).start();
    }

    public void collideDetection(Ball ball , Coordinate newPosition) {
        ArrayList<VO> objectsCollide;


//      Comprobar colision con entre objetos
        objectsCollide = findCollisions( ball ,ball.radio);

//      Si no hay colision , seteamos la posision
        if(objectsCollide.size()== 0 ){
            ball.position = newPosition;

        }else {
//      Y si hay colision lo enviamos al collideManagement
            this.controller.collideManagement(objectsCollide);
            objectsCollide.clear();
        }





        if (ball.position.x < 0) {
            ball.bounce();
            ball.position.x = ball.radio;
        } else if (ball.position.x > this.controller.view.getWidth() - ball.radio) {
            ball.bounce();
            ball.position.x = this.controller.view.getWidth() - ball.radio;
        }

        if (ball.position.y > this.controller.view.getHeight() - ball.radio) {
            ball.bounce();
            ball.position.y = this.controller.view.getHeight() - ball.radio;
        } else if (ball.position.y < 0) {
            ball.bounce();
            ball.position.y = ball.radio;
        }


    }

    private ArrayList<VO> findCollisions(VO vObject, int ballRadio) {
        ArrayList<VO> objectsCollide = new ArrayList<>();

        for (VO otherObject : objects) {
            if (otherObject != vObject) {
                double distance = Math.sqrt(
                        Math.pow((vObject.getPosition().x - otherObject.getPosition().x), 2) +
                                Math.pow((vObject.getPosition().y - otherObject.getPosition().y), 2)
                );
                double totalRadius = ballRadio * 2;

                if (distance < totalRadius) {
                    objectsCollide.add(otherObject);
                    objectsCollide.add(vObject);
                }
            }
        }

        return objectsCollide;
    }

}
