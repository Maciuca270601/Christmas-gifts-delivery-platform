package write;

import entities.Child;
import java.util.ArrayList;

public final class SaveAnnualArray {

    private final ArrayList<Child> children;

    public SaveAnnualArray() {
        this.children = new ArrayList<>();
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    /**
     * This method adds all children from list to this.children.
     */
    public void addChildren(final ArrayList<Child> list) {
        for (Child child: list) {
            this.children.add(new Child(child));
        }
    }
}
