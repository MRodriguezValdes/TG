    package org.example;

    import java.awt.*;
    import java.util.Random;

    public class Ball implements VO, Runnable {
        Random random = new Random();
        public final int[] moveX = {-1, 1, 0, 0, -1, -1, 1, 1};
        public final int[] moveY = {0, 0, -1, 1, 1, -1, 1, -1};
        public Coordinate position;
        int radius = 20;
        boolean isAlive;
        private int direction;
        private int velocity = 5;
        private double acceleration = 10;
        private double mass;
        private LM model;

        public Ball(int x, int y, LM model) {
            this.position = new Coordinate();
            this.setDirection(random.nextInt(moveX.length));
            this.model = model;
            this.isAlive = true;


            this.position.x = x;
            this.position.y = y;
        }

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        @Override
        public boolean isAlive() {
            return isAlive;
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
            g.fillOval(position.x - 10, position.y - 10, radius * 2, radius * 2);
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
        public BoundingBox getBoundingBox() {
            int x1 = position.x - radius;
            int y1 = position.y - radius;
            int x2 = position.x + radius;
            int y2 = position.y + radius;

            return new BoundingBox(x1, y1, x2, y2);
        }


        @Override
        public void bounce() {
            int[] nextDirection;
            switch (this.direction){
                case 0:
                    nextDirection = new int[]{1, 6, 7};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
                case 1:
                    nextDirection = new int[]{0, 4, 5};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
                case 2 :
                    nextDirection = new int[]{3, 4, 6};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
                case 3:
                    nextDirection = new int[]{2, 5, 7};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
                case 4:
                    nextDirection = new int[]{7,1,2};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
                case 5 :
                    nextDirection = new int[]{ 6,1,3};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
                case 6:
                    nextDirection = new int[]{5,0,2};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
                case 7:
                    nextDirection = new int[]{4, 0, 3};
                    this.direction =nextDirection[random.nextInt(3)];
                    break;
            }

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
