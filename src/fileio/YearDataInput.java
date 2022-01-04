package fileio;

import java.util.ArrayList;

public final class YearDataInput {
    private final Double newSantaBudget;
    private final ArrayList<GiftInput> newGifts;
    private final ArrayList<ChildInput> newChildren;
    private final ArrayList<ChildUpdateInput> childrenUpdates;
    private final String strategy;

    public YearDataInput(final Double newSantaBudget, final ArrayList<GiftInput> newGifts,
                         final ArrayList<ChildInput> newChildren,
                         final ArrayList<ChildUpdateInput> childrenUpdates,
                         final String strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public ArrayList<GiftInput> getNewGifts() {
        return newGifts;
    }

    public ArrayList<ChildInput> getNewChildren() {
        return newChildren;
    }

    public ArrayList<ChildUpdateInput> getChildrenUpdates() {
        return childrenUpdates;
    }

    public String getStrategy() {
        return strategy;
    }
}
