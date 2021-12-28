package write;

import entities.Child;

import java.util.ArrayList;

public class SaveAnnualArray {

    private ArrayList<Child> children;

    public SaveAnnualArray() {
        this.children = new ArrayList<>();
    }

    public ArrayList<Child> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Child> children) {
        this.children = children;
    }
}
