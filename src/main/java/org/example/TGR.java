package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TGR {
    public TGR() {

    }

    public void takeDecision(List<VO> objectsCollide) {

        for (int i = 0; i < objectsCollide.size() - 1; i += 2) {
            VO object1 = objectsCollide.get(i);
            VO object2 = objectsCollide.get(i + 1);

            if (object1 instanceof Ball && object2 instanceof Ball) {
                Ball ball1 = (Ball) object1;
                Ball ball2 = (Ball) object2;

                ball1.bounce();
                ball2.kill();
            }
        }
    }

}
