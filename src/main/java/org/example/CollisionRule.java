package org.example;

@FunctionalInterface
public interface CollisionRule {
    void applyRule(VO object1,VO object2);
}
