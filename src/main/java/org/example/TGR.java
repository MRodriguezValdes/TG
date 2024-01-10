package org.example;

import java.util.ArrayList;
import java.util.Random;

public class TGR {
    public static void takeDecision(ArrayList<VO> objectsCollide, LC controller) {

        for (int i = 0; i < objectsCollide.size() - 1; i += 2) {
            VO object1 = objectsCollide.get(i);
            VO object2 = objectsCollide.get(i + 1);

            if (object1 instanceof Ball && object2 instanceof Ball) {
                Ball ball1 = (Ball) object1;
                Ball ball2 = (Ball) object2;

                ball1.bounce();
                ball2.kill();
                controller.model.objects.remove(ball2);
            }
        }
    }

}
