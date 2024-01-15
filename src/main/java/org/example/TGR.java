package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TGR {
    private Map<Class<?>, Map<Class<?>, CollisionRule>> ruleMap = new HashMap<>();

    public TGR() {

        addRule(Ball.class, Ball.class, (object1, object2) -> {
            Ball ball1 = (Ball) object1;
            Ball ball2 = (Ball) object2;
            ball1.bounce();
            ball2.kill();
        });



    }

    public void takeDecision(List<VO> objectsCollide) {
        for (int i = 0; i < objectsCollide.size() - 1; i += 2) {
            VO object1 = objectsCollide.get(i);
            VO object2 = objectsCollide.get(i + 1);

            CollisionRule rule = ruleMap.getOrDefault(object1.getClass(), new HashMap<>()).get(object2.getClass());
            if (rule != null) {
                rule.applyRule(object1, object2);
            }
        }
    }

    private void addRule(Class<?> class1, Class<?> class2, CollisionRule rule) {
        ruleMap.computeIfAbsent(class1, k->new HashMap<>()).put(class2, rule);
        ruleMap.computeIfAbsent(class2, k->new HashMap<>()).put(class1, rule); // Para permitir bidireccionalidad
    }

}
