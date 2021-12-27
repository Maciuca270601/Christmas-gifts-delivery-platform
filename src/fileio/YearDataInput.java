package fileio;

import java.util.ArrayList;

public class YearDataInput {
    private Double newSantaBudget;
    private ArrayList<GiftInput> newGifts;
    private ArrayList<ChildInput> newChildren;
    private ArrayList<ChildUpdateInput> childrenUpdates; //updates the existing list of children

    public YearDataInput(Double newSantaBudget, ArrayList<GiftInput> newGifts, ArrayList<ChildInput> newChildren,
                         ArrayList<ChildUpdateInput> childrenUpdates) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
    }

    public Double getNewSantaBudget() { return newSantaBudget; }

    public ArrayList<GiftInput> getNewGifts() { return newGifts; }

    public ArrayList<ChildInput> getNewChildren() { return newChildren; }

    public ArrayList<ChildUpdateInput> getChildrenUpdates() { return childrenUpdates; }
}
