package visitor;

import entities.Child;

public class AgeCategoryVisitor implements Visitor{

    @Override
    public void visit(Child child) {
        if (child.getAge() < 5) {
            child.setCategory("Baby");
        }
        if (child.getAge() >= 5 && child.getAge() < 12) {
            child.setCategory("Kid");
        }
        if (child.getAge() >= 12 && child.getAge() <= 18) {
            child.setCategory("Teen");
        }
        if (child.getAge() > 18) {
            child.setCategory("YoungAdult");
        }
     }
}
