package visitor;

import entities.Child;

public interface Visitor {
    /**
     * Visits the child.
     */
    void visit(Child child);
}
